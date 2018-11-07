package com.venturedive.rotikhilao.service.order;

import com.venturedive.rotikhilao.DTO.CreateOrderDto;
import com.venturedive.rotikhilao.DTO.OrderDto;

public interface IOrderService {
    OrderDto orderFoodItems(CreateOrderDto createOrderDtos);
}
