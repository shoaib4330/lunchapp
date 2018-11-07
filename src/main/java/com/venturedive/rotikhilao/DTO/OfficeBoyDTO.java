package com.venturedive.rotikhilao.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeBoyDTO {
    private Long id;
    private String officeBoyName;
    private Long companyId;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;

}
