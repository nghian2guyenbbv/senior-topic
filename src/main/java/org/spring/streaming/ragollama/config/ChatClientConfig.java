package org.spring.streaming.ragollama.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {
  @Bean
  public ChatClient configChatClient(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory,
      QuestionAnswerAdvisor questionAnswerAdvisor, RetrievalAugmentationAdvisor retrievalAugmentationAdvisor) {
    Advisor logAdvisor = new SimpleLoggerAdvisor();
    Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
    return chatClientBuilder.defaultAdvisors(List.of(logAdvisor, memoryAdvisor, retrievalAugmentationAdvisor)).build();
  }

  @Bean
  public QuestionAnswerAdvisor questionAnswerAdvisor(VectorStore vectorStore) {
    return QuestionAnswerAdvisor.builder(vectorStore).build();
  }

  @Bean
  public RetrievalAugmentationAdvisor retrievalAugmentationAdvisor(VectorStore vectorStore) {
    return RetrievalAugmentationAdvisor.builder()
        .documentRetriever(VectorStoreDocumentRetriever.builder().vectorStore(vectorStore).build()).build();
  }
}
