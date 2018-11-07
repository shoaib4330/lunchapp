package com.venturedive.rotikhilao.DTO;

import lombok.Data;

@Data
public class CustomerDto {
    private Long customerId;
    private String customerName;
    private String email;
    private String companyName;
    private String pictureUrl;
}
