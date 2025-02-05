package com.example.repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import com.example.entity.Message;
import java.util.List;


public interface MessageRepository extends JpaRepository< Message, Integer>{
  
    List<Message> findByPostedBy(Integer PostedBy);


    @Transactional
    @Modifying (clearAutomatically = true)
    @Query("DELETE FROM Message m WHERE m.messageId = :messageId")
    int deleteMessageById (@Param("messageId") int messageId);


//getmessagebyid

    //List<Message> findByPostedBy(Integer postedBy);

// getMessageByMessageId

    Optional<Message>findById (Integer id);

}
  
