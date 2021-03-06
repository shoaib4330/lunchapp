package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.entitiy.Company;
import com.venturedive.rotikhilao.entitiy.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
    List<Customer> findByCompany(Company company);
}
