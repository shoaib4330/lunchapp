package com.venturedive.rotikhilao.service;

import com.venturedive.rotikhilao.DAO.FoodItem.FoodItemDAO;
import com.venturedive.rotikhilao.DAO.FoodItem.IFoodItemDAO;
import com.venturedive.rotikhilao.DTO.CreateMenuDTO;
import com.venturedive.rotikhilao.enums.FoodItemStatus;
import com.venturedive.rotikhilao.exception.ApplicationException;
import com.venturedive.rotikhilao.mapper.MenuMapper;
import com.venturedive.rotikhilao.model.FoodItem;
import com.venturedive.rotikhilao.model.Vendor;
import com.venturedive.rotikhilao.pojo.BooleanResponse;
import com.venturedive.rotikhilao.DTO.MenuDTO;

import java.util.List;
import java.util.Optional;

public class MenuService implements IMenuService{

    public MenuDTO createMenu(CreateMenuDTO createMenuDTO){
        //1. do validation for vendor (TODO)
        //2. Iterate each item and add it to FoodItem table
        IFoodItemDAO foodItemDAO = new FoodItemDAO();
        for (FoodItem item : items.getData()){
            item.setStatus(FoodItemStatus.ACTIVE.value());
            item.setVendorId(vendorId);
            foodItemDAO.saveFoodItem(item);
        }
        return retrieveVendorMenu(vendorId);
    }

    @Override
    public MenuDTO retrieveVendorMenu(Long vendorId) {
        return null;
    }

    @Override
    public MenuDTO getMenuById(Long menuId) {
        return null;
    }

    @Override
    public MenuDTO getAllMenus() {
        return null;
    }

    public BooleanResponse updateMenu(Long vendorId, Long itemId, FoodItem foodItem){

        // 1. access fooditem
        // 2. check if it belongs to the current vendor
        // 3. Respond accordingly

        FoodItem savedFoodItem = validateFoodItemChangeRequest(vendorId, itemId);

        if (foodItem.getPrice() != null){
            savedFoodItem.setPrice(foodItem.getPrice());
        }
        if (foodItem.getTitle() != null){
            savedFoodItem.setTitle(foodItem.getTitle());
        }

        new FoodItemDAO().saveFoodItem(savedFoodItem);

        return BooleanResponse.success();

    }

    public BooleanResponse deleteMenuItem(Long vendorId, Long itemId){

        FoodItem foodItem = validateFoodItemChangeRequest(vendorId, itemId);

        foodItem.setStatus(FoodItemStatus.DISCONTINUED.value());
        new FoodItemDAO().saveFoodItem(foodItem);

        return BooleanResponse.success();

    }

    public MenuDTO displayMenu(Long vendorId){

        List<FoodItem> activeMenuItems = foodItemRepository.findAllByVendorIdAndStatus(vendorId, FoodItemStatus.ACTIVE.value());
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);


        if (!vendor.isPresent()) {
            throw new ApplicationException("No data present against the provided vendor ID");
        }

        MenuDTO menuDTO = new MenuDTO();

        menuDTO.setItems(MenuMapper.wrapFoodItems(activeMenuItems));
        if (vendor.get().getName() != null) {
            menuDTO.setVendorName(vendor.get().getName());
        }
        return menuDTO;
    }

    private FoodItem validateFoodItemChangeRequest(Long vendorId, Long itemId){

        FoodItem savedFoodItem = new FoodItemDAO().fetchFoodItemById(itemId);

        if (!savedFoodItem.getVendorId().equals(vendorId)){
            throw new Exception("Sorry! You're not authorized to update foodItem with id: " + itemId);
        }
        return savedFoodItem;
    }
}
