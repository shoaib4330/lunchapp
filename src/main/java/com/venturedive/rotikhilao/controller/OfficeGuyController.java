package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.dto.OrderDto;
import com.venturedive.rotikhilao.dto.UpdateCustomerBalanceDto;
import com.venturedive.rotikhilao.service.OfficeGuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/officeGuy")
public class OfficeGuyController {

    @Autowired
    private OfficeGuyService officeGuyService;

    @GetMapping(value = "/{officeGuyId}/orders-to-receive")
    public List<OrderDto> getOrdersToReceive(@PathVariable(value = "officeGuyId") Long officeGuyId){
        return officeGuyService.ordersToBeReceived(officeGuyId);
    }

    @PatchMapping(value = "/update-customer-credit")
    public void creditReceivedFromCustomer(UpdateCustomerBalanceDto customerCreditDto){
        officeGuyService.creditReceivedFromCustomer(customerCreditDto);
    }

    @PatchMapping(value = "/{officeGuyId}/mark-received")
    public void markOrderReceived(Long orderId){
        officeGuyService.markOrderAsReceived(orderId);
    }
}
