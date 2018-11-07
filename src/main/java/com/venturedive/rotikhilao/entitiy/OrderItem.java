package com.venturedive.rotikhilao.entitiy;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="order_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 4801325444819644742L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
