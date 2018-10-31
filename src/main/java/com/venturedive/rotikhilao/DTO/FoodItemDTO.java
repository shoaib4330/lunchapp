package com.venturedive.rotikhilao.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDTO {
    private Integer quantity;
    private Integer unitPrice;
    private Long id;
    private Long vendorId;
    private String title;
    private String imageUrl;
}
