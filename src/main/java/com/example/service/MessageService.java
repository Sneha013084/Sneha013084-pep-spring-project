package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import java.util.Optional;


@Service

public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private AccountRepository accountRepository;

public Message createMessage( Message message){

    if (message.getMessageText() ==null|| message.getMessageText().isEmpty()|| message.getMessageText().length()>255){

        throw new IllegalArgumentException ("Message text must be between 1 and 255 characters");
    }
        Integer userId = message.getPostedBy();
      Optional<Account> user =accountRepository.findById(userId);
       if(user.isEmpty()){

        throw new IllegalArgumentException(" User doesnot exist.");
       }
       return messageRepository.save(message);

    }
}



