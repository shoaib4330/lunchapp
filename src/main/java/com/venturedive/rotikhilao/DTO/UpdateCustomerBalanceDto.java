package com.venturedive.rotikhilao.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerBalanceDto {
    private Long customerId;
    private Long officeBoyId;
    private Integer amount;
}
