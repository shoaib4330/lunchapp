package com.venturedive.rotikhilao.mapper;

import com.venturedive.rotikhilao.dto.FoodItemDTO;
import com.venturedive.rotikhilao.entitiy.FoodItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {
    FoodItemDTO mapToDto(FoodItem foodItem);
    List<FoodItemDTO> mapToDtoList(List<FoodItem> foodItems);
}
