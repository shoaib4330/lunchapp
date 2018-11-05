package com.venturedive.rotikhilao.entitiy;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name="food_item")
@Entity
@Data
public class FoodItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="price")
    private Integer unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "vendor_id",insertable = false, updatable = false, nullable = false)
    private Vendor vendor;

    @Column(name="status")
    private Short status;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name = "quantity")
    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime dtCreated;

    @UpdateTimestamp
    private LocalDateTime dtUpdated;
}
