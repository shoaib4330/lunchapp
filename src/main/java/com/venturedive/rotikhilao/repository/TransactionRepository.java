package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.entitiy.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
