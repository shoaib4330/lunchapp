package com.venturedive.rotikhilao.entitiy;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@Table(name = "office_boy")
public class OfficeBoy implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "office_boy_name")
    private String officeBoyName;

    @Column(name = "office_boy_password")
    private String officeBoyPassword;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="companyId", nullable = false)
    private Company company;

    @CreationTimestamp
    private LocalDateTime dtCreated;

    @UpdateTimestamp
    private LocalDateTime dtUpdated;
}