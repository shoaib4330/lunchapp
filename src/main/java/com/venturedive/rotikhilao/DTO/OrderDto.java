package com.venturedive.rotikhilao.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Long officeBoyId;
    private Long vendorId;
    private Integer bill;
    private String customerName;
    private Long orderStatus;
    private List<FoodItemDTO> foodItems;
}
