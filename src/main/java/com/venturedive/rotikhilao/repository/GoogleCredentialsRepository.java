package com.venturedive.rotikhilao.repository;

import com.venturedive.rotikhilao.entitiy.GoogleCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoogleCredentialsRepository extends JpaRepository<GoogleCredentials, String> {


    Optional<GoogleCredentials> findBySub(String sub);

}
