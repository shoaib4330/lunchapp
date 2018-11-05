package com.venturedive.rotikhilao.entitiy;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Data
@Builder
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name="customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String email;

    @Column(name="image_url")
    private String imageUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="companyId", nullable = false)
    private Company company;

    @CreationTimestamp
    private LocalDateTime dtCreated;

    @UpdateTimestamp
    private LocalDateTime dtUpdated;


}