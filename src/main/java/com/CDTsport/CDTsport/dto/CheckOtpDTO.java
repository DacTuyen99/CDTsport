package com.CDTsport.CDTsport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckOtpDTO {
    private String email;
    private String otp;
}
