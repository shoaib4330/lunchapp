package com.venturedive.rotikhilao.service.vendor;

import com.venturedive.rotikhilao.DTO.CreateVendorDTO;
import com.venturedive.rotikhilao.DTO.FoodItemDTO;
import com.venturedive.rotikhilao.DTO.VendorDTO;
import com.venturedive.rotikhilao.exception.ApplicationException;
import com.venturedive.rotikhilao.mapper.FoodItemMapper;
import com.venturedive.rotikhilao.mapper.VendorMapper;
import com.venturedive.rotikhilao.model.entitiy.FoodItem;
import com.venturedive.rotikhilao.model.entitiy.Vendor;
import com.venturedive.rotikhilao.repository.FoodItemRepository;
import com.venturedive.rotikhilao.repository.VendorRepository;
import com.venturedive.rotikhilao.service.IVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService implements IVendorService {

    @Autowired
    FoodItemRepository foodItemRepository;
    @Autowired
    VendorRepository vendorRepository;
    @Autowired
    private VendorMapper vendorMapper;
    @Autowired
    private FoodItemMapper foodItemMapper;

    @Override
    public VendorDTO createVendor(CreateVendorDTO createVendorDTO) {
        Vendor vendor = Vendor.builder().name(createVendorDTO.getName()).build();
        vendor = vendorRepository.save(vendor);
        return vendorMapper.mapToDto(vendor);
    }

    @Override
    public void deleteVendor(Long vendorId) {
        Vendor vendor = findVendor(vendorId);
        vendorRepository.delete(vendor);
    }

    @Override
    public VendorDTO getVendorById(Long vendorId) {
        return vendorMapper.mapToDto(findVendor(vendorId));
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorMapper.mapToListDto(vendorRepository.findAll());
    }

    @Override
    public List<FoodItemDTO> getAllFoodItemsByVendor(Long vendorId) {
        List<FoodItem> foodItems = foodItemRepository.findAllByVendorId(findVendor(vendorId).getId());
        return foodItemMapper.mapToDtoList(foodItems);
    }

    private Vendor findVendor(Long vendorId){
        return vendorRepository.findById(vendorId)
                .orElseThrow(()-> new ApplicationException("Vendor not found for given VendorID"));
    }
}
