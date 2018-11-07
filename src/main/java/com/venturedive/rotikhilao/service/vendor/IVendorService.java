package com.venturedive.rotikhilao.service.vendor;

import com.venturedive.rotikhilao.dto.*;

import java.util.List;

public interface IVendorService {

    UserTokenResponseDto authenticateVendor(LoginDto loginDto);

    VendorDTO createVendor(CreateVendorDTO createVendorDTO);

    void deleteVendor(Long vendorId);

    VendorDTO getVendorById(Long vendorId);

    List<VendorDTO> getAllVendors();

    List<FoodItemDTO> getAllFoodItemsByVendor(Long vendorId);
}
