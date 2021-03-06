package com.venturedive.rotikhilao.mapper;

import com.venturedive.rotikhilao.DTO.OfficeBoyDTO;
import com.venturedive.rotikhilao.entitiy.OfficeBoy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfficeBoyMapper {
    OfficeBoyDTO mapToDto(OfficeBoy officeBoy);
}
