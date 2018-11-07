package com.venturedive.rotikhilao.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfficeBoyDto {
    private String name;
    private String password;
    private Long companyId;
}
