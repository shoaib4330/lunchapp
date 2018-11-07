package com.venturedive.rotikhilao.entitiy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "office_boy_id")
    private Long officeGuyId;

    private Integer amount;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false, nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_boy_id", insertable = false, updatable = false, nullable = false)
    private OfficeBoy officeGuy;
}
