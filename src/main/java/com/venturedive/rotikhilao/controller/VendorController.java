package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.DTO.CreateVendorDTO;
import com.venturedive.rotikhilao.DTO.FoodItemDTO;
import com.venturedive.rotikhilao.DTO.VendorDTO;
import com.venturedive.rotikhilao.model.entitiy.FoodItem;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;
import com.venturedive.rotikhilao.service.vendor.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    // 1. view current menu
    // 2. create new menu
    // 3. update menu
    // 4. remove from menu
    // 5. revenue related?

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
    public void createVendor(@RequestBody CreateVendorDTO vendorDTO){
        vendorService.createVendor(vendorDTO);
    }


}
