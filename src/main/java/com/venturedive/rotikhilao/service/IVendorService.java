package com.venturedive.rotikhilao.service;

import com.venturedive.rotikhilao.DTO.CreateVendorDTO;
import com.venturedive.rotikhilao.DTO.MenuDTO;
import com.venturedive.rotikhilao.DTO.VendorDTO;
import com.venturedive.rotikhilao.model.Vendor;

import java.util.List;

public interface IVendorService {

    VendorDTO createVendor(CreateVendorDTO createVendorDTO);

    void deleteVendor(Long vendorId);

    VendorDTO getVendorById(Long vendorId);

    List<VendorDTO> getAllVendors();

    List<MenuDTO> getAllMenusByVendorId(Long vendorId);
}
