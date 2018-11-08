package com.venturedive.rotikhilao.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemDTO {
    private Integer quantity;
    private Integer unitPrice;
    private Long foodItemId;
    private Long vendorId;
    private String title;
    private String imageUrl;
    private LocalDate dtCreated;
    private LocalDateTime dtUpdated;

}
