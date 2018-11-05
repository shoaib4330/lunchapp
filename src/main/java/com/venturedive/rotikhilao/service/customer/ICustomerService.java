package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.DTO.CustomerDto;

import java.util.List;

public interface ICustomerService {
    CustomerDto getCustomerById(long customerId);
    List<CustomerDto> getAllCustomers();
}
