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
      
      if (account.getUsername() == null || account.getUsername().isEmpty()){

        throw new IllegalArgumentException("Username cannot be blank.");
      }
      
      if(account.getPassword() == null || account.getPassword().length()<4){
       
        throw new IllegalArgumentException("Password must be at least 4 characters long.");
    
      }
        // Check if the username already exists in the database
    
      Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
       if(existingAccount.isPresent()){
        throw new RuntimeException("Username already exists.");
    }
       // Save the account to the database

      return accountRepository.save(account);
  }  
 
    //login
     
    public Account loginAccount(Account account){

      Optional <Account>  existingAccount = accountRepository.findByUsername(account.getUsername());
      if ( existingAccount.isEmpty()){
        throw new RuntimeException ( "Invalid username or password");
      }

      Account userAccount = existingAccount.get();
      if( !userAccount.getPassword().equals(account.getPassword())){
       
        throw new RuntimeException ( "Invalid username or password");
      }
       return userAccount;

    }
    }