package com.venturedive.rotikhilao.DTO;

import com.venturedive.rotikhilao.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class FoodItemUpdateDTO {
    private Long id;
    private Long vendorId;
    private Long menuId;
    private Integer quantity;
    private String title;
    private Integer price;
    private Short status;
    private String imageUrl;
    private List<OrderItem> items;
}
