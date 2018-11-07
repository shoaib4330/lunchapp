package com.venturedive.rotikhilao.DTO;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class CustomerDto {
    private Long customerId;
    private String customerName;
    private String email;
    private String companyName;
    private String pictureUrl;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;

}
