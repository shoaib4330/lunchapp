package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.DTO.CreateOfficeBoyDto;
import com.venturedive.rotikhilao.DTO.OfficeBoyDTO;
import com.venturedive.rotikhilao.DTO.OrderDto;
import com.venturedive.rotikhilao.DTO.UpdateCustomerBalanceDto;
import com.venturedive.rotikhilao.service.officeboy.OfficeBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/officeBoy")
public class OfficeBoyController {

    @Autowired
    private OfficeBoyService officeGuyService;

    @GetMapping(value = "/{officeBoyId}/orders-to-receive")
    public List<OrderDto> getOrdersToReceive(@PathVariable(value = "officeBoyId") Long officeBoyId){
        return officeGuyService.ordersToBeReceived(officeBoyId);
    }

    @PatchMapping(value = "/update-customer-credit")
    public void creditReceivedFromCustomer(UpdateCustomerBalanceDto customerCreditDto){
        officeGuyService.creditReceivedFromCustomer(customerCreditDto);
    }

    @PatchMapping(value = "/{officeGuyId}/mark-received")
    public void markOrderReceived(Long orderId){
        officeGuyService.markOrderAsReceived(orderId);
    }

    @PostMapping(value = "/add-officeboy")
    public OfficeBoyDTO addOfficeBoy(@RequestBody CreateOfficeBoyDto createOfficeBoyDto){
        return officeGuyService.addOfficeBoy(createOfficeBoyDto);
    }
}
