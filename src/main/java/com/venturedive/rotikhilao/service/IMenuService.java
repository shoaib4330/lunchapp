package com.venturedive.rotikhilao.service;

import com.venturedive.rotikhilao.DTO.CreateMenuDTO;
import com.venturedive.rotikhilao.DTO.MenuDTO;
import com.venturedive.rotikhilao.model.FoodItem;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.pojo.ResponseList;

public interface IMenuService {

    MenuDTO getMenuById(Long menuId);

    MenuDTO getAllMenus();

    MenuDTO createMenu(CreateMenuDTO createMenuDTO);

    //todo: remove this api, vendor api returns menus of a specific vendor
    MenuDTO retrieveVendorMenu(Long vendorId);

    BooleanResponse updateMenu(Long vendorId, Long itemId, FoodItem foodItem);

    BooleanResponse deleteMenuItem(Long vendorId, Long itemId);

    MenuDTO displayMenu(Long vendorId);

    FoodItem validateFoodItemChangeRequest(Long vendorId, Long itemId);
}
