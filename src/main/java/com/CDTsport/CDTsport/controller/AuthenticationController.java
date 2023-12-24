package com.CDTsport.CDTsport.controller;

import com.CDTsport.CDTsport.auth.AuthenticationRequest;
import com.CDTsport.CDTsport.auth.AuthenticationResponse;
import com.CDTsport.CDTsport.dto.ChangePassword;
import com.CDTsport.CDTsport.dto.RegisterUserDTO;
import com.CDTsport.CDTsport.entity.EmailOTP;
import com.CDTsport.CDTsport.entity.User;
import com.CDTsport.CDTsport.entity.Role;
import com.CDTsport.CDTsport.repository.UserRepository;
import com.CDTsport.CDTsport.service.AuthenticationService;
import com.CDTsport.CDTsport.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserRepository userRepository;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserDTO registerUserDTO){
        return ResponseEntity.ok(userService.saveUser(registerUserDTO));
    }
    @PostMapping("/send-otp")
    public ResponseEntity<EmailOTP> sendOTP(@RequestBody EmailOTP emailOTP){
        return ResponseEntity.ok(userService.sendOTP(emailOTP));
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassword changePassword, Authentication authentication){
        userService.changePassword(changePassword,authentication);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("123".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String email = decodedJWT.getSubject();
                User user = userRepository.findUserByEmail(email).get();
                String access_token = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 50*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            }catch (Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
        } else {
            throw  new RuntimeException("Refresh token is missing");
        }
    }
}
