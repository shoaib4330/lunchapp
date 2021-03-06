package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.DTO.*;
import com.venturedive.rotikhilao.service.vendor.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping(value = "/{vendorId}/foodItems")
    public List<FoodItemDTO> getFoodItemsByVendor(@PathVariable(value="vendorId") Long vendorId ) throws Exception {
        // should be visible for customers as well
        return vendorService.getAllFoodItemsByVendor(vendorId);
    }

    @GetMapping(value = "/{vendorId}")
    public VendorDTO getVendorById(@PathVariable(value="vendorId") Long vendorId ){
        return vendorService.getVendorById(vendorId);
    }

    @DeleteMapping(value = "/{vendorId}")
    public void deleteVendorById(@PathVariable(value="vendorId") Long vendorId ){
        vendorService.deleteVendor(vendorId);
    }

    @PostMapping(value = "/add-vendor")
    public VendorDTO createVendor(@RequestBody CreateVendorDTO vendorDTO){
        return vendorService.createVendor(vendorDTO);
    }

    @GetMapping(value = "/{vendorId}/orders")
    public List<OrderDto> getVendorOrders(@PathVariable(value = "vendorId") Long vendorId){
        //todo: implement in service and make a call from here
        return null;
    }

    @PostMapping(value = "/add-foodItem")
    private FoodItemDTO addFoodItem(@RequestBody @NotNull CreateFoodItemDto createFoodItemDto ){
        return vendorService.addFoodItem(createFoodItemDto);
    }

}
