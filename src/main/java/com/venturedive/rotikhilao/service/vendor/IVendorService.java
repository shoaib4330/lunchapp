package com.venturedive.rotikhilao.service.vendor;

import com.venturedive.rotikhilao.DTO.*;

import java.util.List;

public interface IVendorService {

    UserTokenResponseDto authenticateVendor(LoginDto loginDto);

    VendorDTO createVendor(CreateVendorDTO createVendorDTO);

    void deleteVendor(Long vendorId);

    VendorDTO getVendorById(Long vendorId);

    List<VendorDTO> getAllVendors();

    List<FoodItemDTO> getAllFoodItemsByVendor(Long vendorId);

    List<FoodItemDTO> getTodayMenuByVendorId(Long VendorId);

    FoodItemDTO addFoodItem(CreateFoodItemDto createFoodItemDto);
}
