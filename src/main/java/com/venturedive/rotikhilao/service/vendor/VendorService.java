package com.venturedive.rotikhilao.service.vendor;

import com.venturedive.rotikhilao.DTO.*;
import com.venturedive.rotikhilao.configuration.JwtTokenProvider;
import com.venturedive.rotikhilao.entitiy.FoodItem;
import com.venturedive.rotikhilao.entitiy.Vendor;
import com.venturedive.rotikhilao.exception.ApplicationException;
import com.venturedive.rotikhilao.mapper.FoodItemMapper;
import com.venturedive.rotikhilao.mapper.VendorMapper;
import com.venturedive.rotikhilao.repository.FoodItemRepository;
import com.venturedive.rotikhilao.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserTokenResponseDto authenticateVendor(LoginDto loginDto) {
        Vendor vendor = vendorRepository.findByName(loginDto.getUsername())
                .orElseThrow(()-> new ApplicationException("Vendor does not exist with username: "+ loginDto.getUsername()));
        if(bCryptPasswordEncoder.matches(loginDto.getPassword(),vendor.getPassword()))
        {
            return UserTokenResponseDto.builder().userId(vendor.getId())
                    .jwtToken(tokenProvider.generateToken(vendor.getId()))
                    .isAuthorized(true)
                    .name(vendor.getName())
                    .build();
        }
        else
        {
            throw new ApplicationException("Invalid Password");
        }

    }

    @Override
    public VendorDTO createVendor(CreateVendorDTO createVendorDTO) {
        validateCreateVendor(createVendorDTO);

        if(vendorRepository.findByName(createVendorDTO.getName()).isPresent()) {
            throw new ApplicationException("Vendor already exist with name: " + createVendorDTO.getName());
        }
        if(vendorRepository.findByName(createVendorDTO.getPhoneNumber()).isPresent()) {
           throw new ApplicationException("Vendor already exist with given PhoneNumber: " + createVendorDTO.getPhoneNumber());
        }
        Vendor vendor = Vendor.builder().name(createVendorDTO.getName())
                .address(createVendorDTO.getAddress())
                .phoneNumber(createVendorDTO.getPhoneNumber())
                .password(bCryptPasswordEncoder.encode(createVendorDTO.getPassword()))
                .build();
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

    private void validateCreateVendor(CreateVendorDTO createVendorDTO){
        if(createVendorDTO.getName().isEmpty())
        {
            throw new ApplicationException("Invalid vendorName");
        }
        if(createVendorDTO.getPassword().isEmpty())
        {
            throw new ApplicationException("Invalid password");
        }
        if(createVendorDTO.getPhoneNumber().isEmpty())
        {
            throw new ApplicationException("Invalid phone number");
        }
        if(createVendorDTO.getAddress().isEmpty())
        {
            throw new ApplicationException("Invalid address");
        }
    }
}
