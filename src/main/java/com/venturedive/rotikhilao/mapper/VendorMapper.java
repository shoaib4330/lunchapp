package com.venturedive.rotikhilao.mapper;

import com.venturedive.rotikhilao.DTO.VendorDTO;
import com.venturedive.rotikhilao.entitiy.Vendor;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface VendorMapper {
    VendorDTO mapToDto(Vendor vendor);
    List<VendorDTO> mapToListDto(List<Vendor> vendors);
}
