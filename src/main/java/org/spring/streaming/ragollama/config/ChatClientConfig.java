package org.spring.streaming.ragollama.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {
  @Bean
  public ChatClient configChatClient(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, VectorStore vectorStore) {
    Advisor logAdvisor = new SimpleLoggerAdvisor();
    Advisor memoryAdvisor = MessageChatMemoryAdvisor
        .builder(chatMemory).build();
    Advisor ragAdvisor = QuestionAnswerAdvisor.builder(vectorStore).build();
    return chatClientBuilder.defaultAdvisors(List.of(logAdvisor, memoryAdvisor, ragAdvisor)).build();
  }

}
