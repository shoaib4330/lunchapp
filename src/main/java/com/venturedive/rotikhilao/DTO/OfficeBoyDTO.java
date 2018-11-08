package com.venturedive.rotikhilao.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficeBoyDTO {
    private Long id;
    private String officeBoyName;
    private Long companyId;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;
}
