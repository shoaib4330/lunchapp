package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.entitiy.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByName(String name);
    Optional<Vendor> findByPhoneNumber(String phoneNumber);
}
