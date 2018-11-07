package com.venturedive.rotikhilao.mapper;

import com.venturedive.rotikhilao.DTO.OrderDto;
import com.venturedive.rotikhilao.entitiy.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "customerName",source = "customer.customerName")
    OrderDto mapToDto(Order order);

    List<OrderDto> mapToDto(List<Order> orderList);
}
