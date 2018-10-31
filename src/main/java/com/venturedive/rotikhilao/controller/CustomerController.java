package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.entitiy.Order;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.pojo.UserProfile;
import com.venturedive.rotikhilao.request.OrderWrapper;
import com.venturedive.rotikhilao.service.customer.ICustomerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@RequestMapping("/api/customers")
public class CustomerController {

    private ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/profile/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get user profile")
    public UserProfile viewProfile(@PathVariable(name= "userId") Long userId) throws Exception {

        return customerService.viewProfile(userId);
    }


    @GetMapping("/orders/{customerId}/current")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get customer's current orders")
    public ResponseList<Order> viewCurrentOrders(@PathVariable(name = "customerId") Long customerId) throws Exception {

        return customerService.viewCurrentOrders(customerId);

    }

    @GetMapping("/orders/{customerId}/all")
    public ResponseList<Order> viewAllOrders(@PathVariable(name = "customerId") Long customerId) throws Exception {

        return customerService.viewAllOrders(customerId);
    }

    @PostMapping("/orders")
    public BooleanResponse orderFood(@RequestBody @Valid @NotNull OrderWrapper request) throws Exception {

        log.info("ORDER FOOD REQUEST RECEIVED");

        return customerService.orderFood(request);
    }


    @DeleteMapping("/orders/{customerId}/{orderId}")
    public BooleanResponse cancelOrder(@PathVariable(name="customerId") Long customerId,
                                       @PathVariable(name="orderId") Long orderId) throws Exception {

        return customerService.cancelOrder(customerId, orderId);

    }


    @PutMapping("/orders/{customerId}/{orderId}")
    public BooleanResponse updateOrder(@PathVariable(name="customerId") Long customerId,
                                       @PathVariable(name="orderId") Long orderId,
                                       @RequestBody @Valid @NotNull OrderWrapper request) throws Exception {

        return customerService.updateOrder(customerId,orderId, request);
    }


}
