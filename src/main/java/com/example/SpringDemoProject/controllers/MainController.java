package com.example.SpringDemoProject.controllers;

import com.example.SpringDemoProject.config.CustomUserDetails;
import com.example.SpringDemoProject.models.Message;
import com.example.SpringDemoProject.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        List<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageService.findByTagEquals(filter);
        } else {
            messages = messageService.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/add")
    public String addMessage(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model) {
        messageService.save(new Message(userDetails.getUser(), text, tag));
        model.put("messages", messageService.findAll());
        return "main";
    }
}
