package com.venturedive.rotikhilao.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private long id;
    private Integer bill;
    private String customerName;
    private Long orderStatus;
    private List<FoodItemDTO> foodItems;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;
}
