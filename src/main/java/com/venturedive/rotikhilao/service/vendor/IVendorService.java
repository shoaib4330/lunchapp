package com.venturedive.rotikhilao.service.vendor;

import com.venturedive.rotikhilao.DTO.CreateVendorDTO;
import com.venturedive.rotikhilao.DTO.FoodItemDTO;
import com.venturedive.rotikhilao.DTO.VendorDTO;

import java.util.List;

public interface IVendorService {

    VendorDTO createVendor(CreateVendorDTO createVendorDTO);

    void deleteVendor(Long vendorId);

    VendorDTO getVendorById(Long vendorId);

    List<VendorDTO> getAllVendors();

    List<FoodItemDTO> getAllFoodItemsByVendor(Long vendorId);
}
