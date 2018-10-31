package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.entitiy.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByEmailDomain(String emailDomain);
}
