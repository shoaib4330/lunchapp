package com.venturedive.rotikhilao.entitiy;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name="food_item")
@Entity
@Data
@Builder
public class FoodItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long foodItemId;

    @Column(name="title")
    private String title;

    @Column(name="price")
    private Integer unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id",insertable = false, updatable = false)
    private Vendor vendor;

    @Column(name="status")
    private Short status;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "vendor_id",nullable = false)
    private Long vendorId;

    @CreationTimestamp
    private LocalDate dtCreated;

    @UpdateTimestamp
    private LocalDateTime dtUpdated;
}
