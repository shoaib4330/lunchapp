package com.venturedive.rotikhilao.entitiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="order_item")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 4801325444819644742L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",insertable = false, updatable = false, nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private FoodItem footItem;

    @CreationTimestamp
    private LocalDateTime dtCreated;

    @UpdateTimestamp
    private LocalDateTime dtUpdated;
}
