package com.venturedive.rotikhilao.model.entitiy;

import com.venturedive.rotikhilao.model.FoodItem;
import com.venturedive.rotikhilao.model.Vendor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
}
