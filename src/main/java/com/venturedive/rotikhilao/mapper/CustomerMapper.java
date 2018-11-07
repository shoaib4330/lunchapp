package com.venturedive.rotikhilao.mapper;

import com.venturedive.rotikhilao.dto.CustomerDto;
import com.venturedive.rotikhilao.entitiy.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "companyName",source = "company.companyName")
    CustomerDto mapToDto(Customer customer);

    List<CustomerDto> mapToDtoList(List<Customer> customers);
}
