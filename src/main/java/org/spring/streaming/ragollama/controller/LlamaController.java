package org.spring.streaming.ragollama.controller;

import org.spring.streaming.ragollama.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
public class LlamaController {
  private final ChatService llamaAiService;

  public LlamaController(ChatService llama) {
    this.llamaAiService = llama;
  }

  @GetMapping("/prompt")
  public String startPrompt(@RequestParam(value = "message") String prompt) {
    return llamaAiService.prompt(prompt);
  }
}
