package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.DTO.CustomerDto;
import com.venturedive.rotikhilao.DTO.UpdateCustomerBalanceDto;

import java.util.List;

public interface ICustomerService {
    CustomerDto getCustomerById(long customerId);
    List<CustomerDto> getAllCustomers();
    List<CustomerDto> getAllCustomersByCompany(Long companyId);
    void creditTransaction(UpdateCustomerBalanceDto customerCreditDto);
    void debitTransaction(UpdateCustomerBalanceDto customerCreditDto);
}
