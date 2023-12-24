package com.CDTsport.CDTsport.repository;


import com.CDTsport.CDTsport.entity.EmailOTP;
import com.CDTsport.CDTsport.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailOTPRepository extends JpaRepository<EmailOTP,Long> {

}
