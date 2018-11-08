package com.venturedive.rotikhilao.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PlaceOrderDto {
    private Long customerId;
    private Long officeBoyId;
    private Long vendorId;
    private List<FoodItemDTO> foodItems;
}
