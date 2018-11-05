package com.venturedive.rotikhilao.service.order;

import com.venturedive.rotikhilao.DTO.CreateOrderDto;
import com.venturedive.rotikhilao.DTO.OrderDto;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

public interface IOrderService {
    OrderDto orderFoodItems(CreateOrderDto createOrderDtos);
}
