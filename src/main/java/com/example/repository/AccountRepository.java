package com.example.repository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Account;
@Repository

public interface AccountRepository extends JpaRepository<Account, Integer> {

Optional<Account> findByUsername( String username);

}


