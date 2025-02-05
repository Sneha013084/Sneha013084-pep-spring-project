package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;//
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;


/**

 * 
 * 
 * 
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
 
 public class SocialMediaController {
    @Autowired 
    private AccountService accountService;

    @PostMapping("/register")

    public ResponseEntity<?>registerAccount ( @RequestBody Account account){

        try{
             Account registeredAccount = accountService.registerAccount(account);
         return new ResponseEntity<>(registeredAccount,HttpStatus.OK);

    } catch(RuntimeException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); // 409 Conflict - Username Exists

    } catch(Exception e){
       return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }      
 }   

 // login //
  @PostMapping("/login")

  public ResponseEntity <?> loginAccount (@RequestBody Account account){
  try{
     Account loggedAccount = accountService.loginAccount(account);
     return new ResponseEntity<>(loggedAccount,HttpStatus.OK );

  }catch (RuntimeException e){
    return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
  }catch (Exception e){
    return new ResponseEntity<>( e.getMessage(),HttpStatus. INTERNAL_SERVER_ERROR);

  }
  }


//Create message //

   @Autowired
  private MessageService messageService ;
  

@PostMapping ("/messages")
 public ResponseEntity <?> createMessage(@RequestBody Message message){
   try{
    
    Message createdMessage = messageService.createMessage(message);
    return new ResponseEntity<>(createdMessage, HttpStatus.OK);

   }catch( IllegalArgumentException e){
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
   } catch ( Exception e){
    return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
   }}
// Delete message
     
     //@Autowired
    // public SocialMediaController(MessageService messageService) {
    //this.messageService = messageService;
  

  //@RequestMapping("/messages")
    
    @DeleteMapping("/messages/{messageId}")

    public ResponseEntity<?> deleteMessage (@PathVariable int messageId){

      int rowsDeleted = messageService.deleteMessageById(messageId);
      if(rowsDeleted > 0 ){
        return ResponseEntity.ok (rowsDeleted);
      }else{
        return ResponseEntity.ok ().build();
      }

      }

      //RetrieveMessagesByAccountId

      @GetMapping("/accounts/{accountId}/messages")

    public ResponseEntity< List<Message>> getMessagesByAccountId(@PathVariable int accountId){

      List<Message>messages = messageService.getMessagesByAccountId(accountId);
      
        return ResponseEntity.ok(messages);
    
      }
    
    //getMessageBymessageId

     @GetMapping("/messages/{messageId}")
     public ResponseEntity<?>getMessageById (@PathVariable int messageId ){

        Optional<Message>messageOpt = messageService.getMessageById(messageId);
        return ResponseEntity.ok(messageOpt.orElse(null));
     }
    }


 





