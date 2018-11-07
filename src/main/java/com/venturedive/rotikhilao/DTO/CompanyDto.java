package com.venturedive.rotikhilao.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private long companyId;
    private String companyName;
    private String address;
    private String emailDomain;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;

}
