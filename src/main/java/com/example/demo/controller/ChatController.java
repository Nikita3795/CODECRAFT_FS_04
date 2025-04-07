 package com.example.demo.controller;

import com.example.demo.model.ChatMessage;
import com.example.demo.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageRepository chatMessageRepository = null;

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(@Payload ChatMessage message) { // ✅ Added @Payload
        return chatMessageRepository.save(message); // ✅ Save & return the message
    }

    @GetMapping("/history/{receiver}")
    public ResponseEntity<List<ChatMessage>> getChatHistory(@PathVariable String receiver) {
        List<ChatMessage> messages = chatMessageRepository.findByReceiver(receiver);
        return ResponseEntity.ok(messages);
    }
}
