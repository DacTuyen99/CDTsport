package com.CDTsport.CDTsport.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "email_otp")
@AllArgsConstructor
public class EmailOTP {
    @Id
    @SequenceGenerator(name = "email_otp_sequence",sequenceName = "email_otp_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_otp_sequence")
    private Long id;
    private String toEmail;
    private String otp;
    private Timestamp timeSendOtp;
    private Boolean statusCheck;
    private Integer countCheck;

}
