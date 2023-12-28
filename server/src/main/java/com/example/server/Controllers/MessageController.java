package com.example.server.Controllers;

import com.example.server.Models.Message;
import com.example.server.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

//    @GetMapping
//    public List<Message> getAllMessages(){
//        return messageService.getAllMessages();
//    }
//
//    @PostMapping
//    public Message getMessageById(@RequestBody Long id){
//        Message message = messageService.getMessageById(id);
//        return message;
//    }
}
