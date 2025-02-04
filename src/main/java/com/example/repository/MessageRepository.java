package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import javax.transaction.Transactional;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository< Message, Integer>{
  
    Optional< Message> findByPostedBy(Integer PostedBy);

    @Transactional
    @Modifying
    @Query("DELETE FROM Message m WHERE m.id = :messageId")
    int deleteMessageById(@Param("messageId") int messageId);
}

//delete message

  
