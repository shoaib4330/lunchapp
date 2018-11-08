package com.venturedive.rotikhilao.DTO;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto implements Serializable {
    private long companyId;
    private String companyName;
    private String address;
    private String emailDomain;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;
}
