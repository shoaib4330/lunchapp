package com.venturedive.rotikhilao.model.entitiy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lb_menu")
public class Menu {
    @Id
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "fk_vendorId")
    private Vendor vendor;

    private List<FoodItem> foodItems;

    @CreationTimestamp
    private LocalDateTime dtCreated;

    @UpdateTimestamp
    private LocalDateTime dtUpdated;

}
