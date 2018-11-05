package com.venturedive.rotikhilao.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CreateOrderDto {
    private Long customerId;
    private List<Long> foodItems;
    private Long officeBoyId;
}
