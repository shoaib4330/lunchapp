package com.venturedive.rotikhilao.service.customer;

import com.venturedive.rotikhilao.DTO.CustomerDto;
import com.venturedive.rotikhilao.entitiy.Company;
import com.venturedive.rotikhilao.entitiy.Customer;
import com.venturedive.rotikhilao.exception.ApplicationException;
import com.venturedive.rotikhilao.mapper.CustomerMapper;
import com.venturedive.rotikhilao.repository.CompanyRepository;
import com.venturedive.rotikhilao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ApplicationException("Customer not found with given id"));
        return customerMapper.mapToDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.mapToDtoList(customers);
    }

    @Override
    public List <CustomerDto> getAllCustomersByCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(()->new ApplicationException("Company not found with given id"));
        List<Customer> customers = customerRepository.findByCompany(company);
        return customerMapper.mapToDtoList(customers);
    }
}
