package com.venturedive.rotikhilao.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorDTO {
    private Long id;
    private String name;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;
}
