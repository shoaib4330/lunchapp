package com.venturedive.rotikhilao.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {
    private Long id;
    private String name;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;
}