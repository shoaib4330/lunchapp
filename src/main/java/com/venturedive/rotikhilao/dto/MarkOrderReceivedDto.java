package com.venturedive.rotikhilao.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MarkOrderReceivedDto {
    private Long orderId;
}
