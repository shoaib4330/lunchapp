package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.entitiy.OfficeBoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficeBoyRepository extends JpaRepository<OfficeBoy, Long> {
    Optional<OfficeBoy> findByOfficeBoyName(String officeBoyName);
}
