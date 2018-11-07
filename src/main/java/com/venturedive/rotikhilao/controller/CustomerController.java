package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.DTO.CustomerDto;
import com.venturedive.rotikhilao.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/{customerId}")
    public CustomerDto getCustumerById(@PathVariable(name= "customerId") long userId) throws Exception {
        return customerService.getCustomerById(userId);
    }


    @GetMapping("/all")
    public List<CustomerDto> getAllCustomer() throws Exception {
        return customerService.getAllCustomers();
    }

}
