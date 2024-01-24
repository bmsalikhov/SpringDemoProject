package com.example.SpringDemoProject.repositories;

import com.example.SpringDemoProject.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {


    List<Message> findByTagEquals(String tag);
}
