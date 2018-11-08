package com.venturedive.rotikhilao.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderDto {
    private Long customerId;
    private Long officeBoyId;
    private Long vendorId;
    private List<FoodItemDTO> foodItems;
}
