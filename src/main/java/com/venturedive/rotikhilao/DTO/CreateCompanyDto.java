package com.venturedive.rotikhilao.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyDto {
    private String companyName;
    private String address;
    private String emailDomain;
}
