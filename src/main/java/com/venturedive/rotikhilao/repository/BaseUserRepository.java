package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.model.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BaseUserRepository<T extends User>  extends JpaRepository<T, Long> {

}
