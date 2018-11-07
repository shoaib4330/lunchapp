package com.venturedive.rotikhilao.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;

}
