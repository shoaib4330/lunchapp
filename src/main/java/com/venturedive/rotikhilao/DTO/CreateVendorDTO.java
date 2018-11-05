package com.venturedive.rotikhilao.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVendorDTO {
    private String name;
    private String password;
    private String phoneNumber;
    private String address;
}
