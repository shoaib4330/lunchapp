package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.DTO.CustomerDto;
import com.venturedive.rotikhilao.DTO.OrderDto;
import com.venturedive.rotikhilao.DTO.PlaceOrderDto;
import com.venturedive.rotikhilao.DTO.UpdateCustomerBalanceDto;
import com.venturedive.rotikhilao.pojo.BooleanResponse;

import java.util.List;

public interface ICustomerService {
    BooleanResponse placeOrder(PlaceOrderDto placeOrderDto);
    CustomerDto getCustomerById(long customerId);
    List<CustomerDto> getAllCustomers();
    List<CustomerDto> getAllCustomersByCompany(Long companyId);
    void creditTransaction(UpdateCustomerBalanceDto customerCreditDto);
    void debitTransaction(UpdateCustomerBalanceDto customerCreditDto);
}
