package com.CDTsport.CDTsport.repository;


import com.CDTsport.CDTsport.entity.EmailOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailOTPRepository extends JpaRepository<EmailOTP,Long> {
    Optional<EmailOTP> findEmailOTPByToEmail(String toEmail);

}
