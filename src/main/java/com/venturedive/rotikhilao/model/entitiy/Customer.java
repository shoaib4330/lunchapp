package com.venturedive.rotikhilao.model.entitiy;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="customer")
@Data
@PrimaryKeyJoinColumn(name="user_id")
public class Customer extends User {

    @Column(name="balance")
    private Integer balance;

}
