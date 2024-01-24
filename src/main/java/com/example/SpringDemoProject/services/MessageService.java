package com.example.SpringDemoProject.services;

import com.example.SpringDemoProject.models.Message;
import com.example.SpringDemoProject.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public void save(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findByTagEquals(String tag) {
        return messageRepository.findByTagEquals(tag);
    }
}
