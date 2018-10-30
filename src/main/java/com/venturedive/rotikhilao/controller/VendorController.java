package com.venturedive.rotikhilao.controller;

import com.venturedive.rotikhilao.model.FoodItem;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.DTO.MenuDTO;
import com.venturedive.rotikhilao.pojo.ResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    // 1. view current menu
    // 2. create new menu
    // 3. update menu
    // 4. remove from menu
    // 5. revenue related?

    @Autowired
    private IVendorService vendorService;

    @GetMapping(value = "/{vendorId}/menu")
    public MenuDTO vendorMenu(@PathVariable(value="vendorId") Long vendorId ) throws Exception {
        // should be visible for customers as well
        return vendorService.retrieveVendorMenu(vendorId);
    }


    @PostMapping(value = "/{vendorId}/menu")
    public MenuDTO createMenu(@RequestBody @NotNull @Valid ResponseList<FoodItem> items, @RequestParam(value="vendorId") Long vendorId) throws Exception {
        return vendorService.createMenu(items, vendorId);
    }

    @PatchMapping(value = "/{vendorId}/menu/{itemId}")
    public BooleanResponse updateMenuItem(@RequestBody @NotNull @Valid FoodItem foodItem,
                                          @PathVariable(name="vendorId") Long vendorId,
                                          @PathVariable(name="itemId") Long itemId) throws Exception {
        return vendorService.updateMenu(vendorId, itemId, foodItem);
    }

    @PatchMapping(value="/{vendorId}/menu/{itemId}/delete")
    public BooleanResponse deleteMenuItem(@PathVariable(name="vendorId") Long vendorId,
                                          @PathVariable(name="itemId") Long itemId) throws Exception {

        log.info("DELETE MENU ITEM REQUEST RECEIVED");

        return vendorService.deleteMenuItem(vendorId, itemId);
    }

}
