package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.DTO.*;
import com.venturedive.rotikhilao.entitiy.FoodItem;
import com.venturedive.rotikhilao.entitiy.Order;
import com.venturedive.rotikhilao.pojo.BooleanResponse;

import java.util.List;

public interface ICustomerService {
    OrderDto placeOrder(CreateOrderDto createOrderDto);
    CustomerDto getCustomerById(long customerId);
    List<CustomerDto> getAllCustomers();
    List<CustomerDto> getAllCustomersByCompany(Long companyId);
    void creditTransaction(UpdateCustomerBalanceDto customerCreditDto);
    void debitTransaction(UpdateCustomerBalanceDto customerCreditDto);
    List<FoodItemDTO> getMenuForToday();
}
