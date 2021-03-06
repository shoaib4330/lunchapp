package com.venturedive.rotikhilao.entitiy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode()
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(name = "user_email")
    private String email;

    @Column(name="image_url")
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime dtCreated;

    @UpdateTimestamp
    private LocalDateTime dtUpdated;

}
