package org.spring.streaming.springollama;

import org.spring.streaming.springollama.service.LlamaAiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ai")
public class LlamaController {
  private final LlamaAiService llamaAiService;
  public LlamaController(LlamaAiService llama) {
    this.llamaAiService = llama;
  }
  @GetMapping("/prompt")
  public String startPrompt(@RequestParam(value = "message") String prompt) {
    return llamaAiService.generateRs(prompt);
  }

}
