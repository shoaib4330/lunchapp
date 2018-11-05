package com.venturedive.rotikhilao.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="orders")
@Data
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="total_bill")
    private Integer bill;

    @Column(name="delivery_time")
    private LocalDateTime deliveryTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", insertable = false, updatable = false)
    @JsonIgnore
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="office_boy_id", insertable = false, updatable = false)
    @JsonIgnore
    private OfficeBoy officeBoy;

    @Column(name="order_status")
    private long orderStatus;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<OrderItem> orderItems;

    @CreationTimestamp
    private LocalDateTime dtCreated;

    @UpdateTimestamp
    private LocalDateTime dtUpdated;
}
