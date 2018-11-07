package com.venturedive.rotikhilao.mapper;

import com.venturedive.rotikhilao.DTO.CompanyDto;
import com.venturedive.rotikhilao.entitiy.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDto mapToDto(Company company);
    List<CompanyDto> mapToListDto(List<Company> companies);
}
