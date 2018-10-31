package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.DTO.CustomerDto;
import com.venturedive.rotikhilao.entitiy.Customer;
import com.venturedive.rotikhilao.entitiy.Order;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.pojo.UserProfile;
import com.venturedive.rotikhilao.request.OrderWrapper;

import java.util.List;

public interface ICustomerService {
    CustomerDto getCustomerById(Long customerId);
    List<CustomerDto> getAllCustomers();
    List<CustomerDto> getAllCustomersByCompany(Long companyId);
}
