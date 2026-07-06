package org.spring.streaming.springollama.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;

@Service
public class LlamaAiService {
  private final OllamaChatModel ollamaModel;

  public LlamaAiService(OllamaChatModel ollamaModel) {
    this.ollamaModel = ollamaModel;
  }

  public String generateRs(String request) {
    var prompt = new Prompt(request, OllamaChatOptions.builder().model("llama2").build());
    ChatResponse response = ollamaModel.call(prompt);
    return response.getResult().getOutput().getText();
  }
}
