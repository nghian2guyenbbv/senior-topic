package org.spring.streaming.ragollama.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
  private final ChatClient chatClient;

  public ChatService(ChatClient client) {
    this.chatClient = client;
  }

  public String prompt(String message) {
    var response = chatClient.prompt(message)
        .advisors(a -> a.param("chat_memory_conversation_id", "default"));
    return response.call().chatResponse().getResult().getOutput().getText();
  }
}
