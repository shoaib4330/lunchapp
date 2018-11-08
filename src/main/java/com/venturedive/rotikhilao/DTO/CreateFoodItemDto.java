package com.venturedive.rotikhilao.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFoodItemDto {
    private Long vendorId;
    private String title;
    private Integer quantity;
    private Integer unitPrice;

}
