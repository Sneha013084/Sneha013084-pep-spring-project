package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service

public class AccountService  {

      
      @Autowired
      private AccountRepository accountRepository;
      
  
      public Account registerAccount( Account account){
      
      if (account.getUsername() ==null||account.getUsername().isEmpty()||account.getPassword().length()<4){
       
       
        throw new IllegalArgumentException("Invalid username or password. Password must be at least 4 characters.");
      }
        
      
    
      Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
       if(existingAccount.isPresent()){
        throw new RuntimeException("Username already exists.");
    }
   
      return accountRepository.save(account);
  }  
}