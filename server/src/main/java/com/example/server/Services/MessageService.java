package com.example.server.Services;

import com.example.server.Models.Message;
import com.example.server.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

//    public List<Message> getAllMessages(){
//        return messageRepository.findAll();
//    }
//
//    public Message getMessageById(Long id){
//        return messageRepository.findById(id);
//    }

}
