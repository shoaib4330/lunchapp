package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.DTO.*;
import com.venturedive.rotikhilao.entitiy.Order;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.service.customer.ICustomerService;
import com.venturedive.rotikhilao.service.vendor.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IVendorService vendorService;

    @GetMapping("/{customerId}")
    public CustomerDto getCustomerById(@PathVariable(name= "customerId") long userId) throws Exception {
        return customerService.getCustomerById(userId);
    }

    @PostMapping(value = "/place-order")
    public OrderDto placeOrder(@RequestBody CreateOrderDto createOrderDto){
        return customerService.placeOrder(createOrderDto);
    }

    @GetMapping("/all")
    public List<CustomerDto> getAllCustomer() throws Exception {
        return customerService.getAllCustomers();
    }

    @GetMapping("/menu/{vendorId}")
    public List<FoodItemDTO> getTodayMenu(@PathVariable Long vendorId)
    {
        return vendorService.getTodayMenuByVendorId(vendorId);
    }

    @GetMapping("/available-vendors")
    public List<VendorDTO> getAvailableVendors(){
        return vendorService.getAllVendors();
    }

}
