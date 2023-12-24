package com.CDTsport.CDTsport.service;


import com.CDTsport.CDTsport.dto.ChangePassword;
import com.CDTsport.CDTsport.dto.RegisterUserDTO;
import com.CDTsport.CDTsport.entity.EmailOTP;
import com.CDTsport.CDTsport.entity.Role;
import com.CDTsport.CDTsport.entity.User;
import com.CDTsport.CDTsport.exception.BaseException;
import com.CDTsport.CDTsport.repository.EmailOTPRepository;
import com.CDTsport.CDTsport.repository.RoleRepository;
import com.CDTsport.CDTsport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JavaMailSender javaMailSender;
    private final EmailOTPRepository emailOTPRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(RegisterUserDTO registerUserDTO){
        validateAccount(registerUserDTO);
        User user = new User();
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByName("ROLE_USER"));
        user.setRoles(roles);
        user.setUser_name(registerUserDTO.getUser_name());
        user.setFull_name(registerUserDTO.getFull_name());
        user.setEmail(registerUserDTO.getEmail());
        return userRepository.save(user);
    }
    public EmailOTP sendOTP(EmailOTP emailOTP){
        Random random = new Random();
        int otpNumber = 100000 + random.nextInt(900000);
        String otp = String.valueOf(otpNumber);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("caotuyen1999vp1@gmail.com");
        simpleMailMessage.setTo(emailOTP.getToEmail());
        simpleMailMessage.setSubject("CDTsport send your OTP");
        simpleMailMessage.setText("Your OTP code is:" + otp);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        javaMailSender.send(simpleMailMessage);
        emailOTP.setOtp(otp);
        emailOTP.setTimeSendOtp(timestamp);
        emailOTP.setStatusCheck(false);
        emailOTPRepository.save(emailOTP);
        return emailOTP;
    }

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public void addToUser(String username, String roleName){
        User user = userRepository.findUserByEmail(username).get();
        Role role = roleRepository.findRoleByName(roleName);
        user.getRoles().add(role);
    }

    public void validateAccount(RegisterUserDTO registerUserDTO){
        if (ObjectUtils.isEmpty(registerUserDTO)){
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"Data  must not empty");
        }
        Optional<User> user = userRepository.findUserByEmail(registerUserDTO.getEmail());
        if(user.isPresent()){
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"Username had existed");
        }
    }

    public User getEmailByAuthentication(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return user;
    }

    public void changePassword(ChangePassword changePassword, Authentication authentication){
        Optional<User> user = userRepository.findUserByEmail(changePassword.getEmail());
        if (user.isPresent() && user.get().getEmail().equals(getEmailByAuthentication(authentication).getEmail())){
            if (passwordEncoder.matches(changePassword.getOldPassword(), user.get().getPassword()) &&
                    changePassword.getNewPassword().equals(changePassword.getConfirmPassword())){
                user.get().setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
                userRepository.save(user.get());
            }else {
                throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"sai password");
            }
        }else {
            throw new BaseException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"email sai");
        }
    }
}
