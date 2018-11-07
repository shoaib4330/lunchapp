package com.venturedive.rotikhilao.entitiy;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vendor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vendor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="username")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "vendor_phone")
    private String phoneNumber;

    @Column(name = "vendor_address")
    private String address;

    @CreationTimestamp
    private LocalDateTime dtCreated;

    @UpdateTimestamp
    private LocalDateTime dtUpdated;

    @OneToMany(
            mappedBy = "vendor",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<FoodItem> foodItems;
}
