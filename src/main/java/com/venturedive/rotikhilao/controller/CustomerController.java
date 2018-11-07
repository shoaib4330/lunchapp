package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.dto.CustomerDto;
import com.venturedive.rotikhilao.service.customer.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/customers")
public class CustomerController {

    private ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/{customerId}")
    public CustomerDto getCustumerById(@PathVariable(name= "customerId") Long userId) throws Exception {
        return customerService.getCustomerById(userId);
    }


    @GetMapping("/all")
    public List<CustomerDto> getAllCustomer() throws Exception {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{companyId}")
    public CustomerDto getCustumerByCompanyId(@PathVariable(name= "companyId") Long companyId) throws Exception {
        return customerService.getCustomerById(companyId);
    }

}
