package com.venturedive.rotikhilao.service.order;

import com.venturedive.rotikhilao.dto.CreateOrderDto;
import com.venturedive.rotikhilao.dto.OrderDto;

public interface IOrderService {
    OrderDto orderFoodItems(CreateOrderDto createOrderDtos);
}
