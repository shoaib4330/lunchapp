package com.venturedive.rotikhilao.DTO;

import com.venturedive.rotikhilao.entitiy.FoodItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDto {
    private long id;
    private Integer bill;
    private String customerName;
    private String orderStatus;
    private List<FoodItem> foodItems;
}
