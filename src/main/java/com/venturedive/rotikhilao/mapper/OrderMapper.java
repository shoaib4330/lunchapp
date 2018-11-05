package com.venturedive.rotikhilao.mapper;

import com.venturedive.rotikhilao.DTO.OrderDto;
import com.venturedive.rotikhilao.entitiy.Order;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "customerName",source = "customer.customerName")
    OrderDto mapToDto(Order order);
}
