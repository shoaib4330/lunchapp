package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.model.entitiy.OfficeBoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeBoyRepository extends JpaRepository<OfficeBoy, Long> {

}
