 package com.example.demo.service;

import com.example.demo.model.ChatMessage;
import com.example.demo.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    
    private final ChatMessageRepository chatMessageRepository = null;

    /**
     * Save a chat message to the database.
     * @param message ChatMessage object to save.
     * @return Saved ChatMessage object.
     */
    public ChatMessage saveMessage(ChatMessage message) {
        return chatMessageRepository.save(message);
    }

    /**
     * Retrieve chat history for a specific user.
     * @param receiver The username of the recipient.
     * @return List of chat messages.
     */
    public List<ChatMessage> getChatHistory(String receiver) {
        return chatMessageRepository.findByReceiver(receiver);
    }
}
