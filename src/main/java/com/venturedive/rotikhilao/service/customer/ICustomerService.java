package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.dto.CustomerDto;
import com.venturedive.rotikhilao.dto.UpdateCustomerBalanceDto;

import java.util.List;

public interface ICustomerService {
    CustomerDto getCustomerById(long customerId);
    List<CustomerDto> getAllCustomers();
    List<CustomerDto> getAllCustomersByCompany(Long companyId);
    void creditTransaction(UpdateCustomerBalanceDto customerCreditDto);
    void debitTransaction(UpdateCustomerBalanceDto customerCreditDto);
}
