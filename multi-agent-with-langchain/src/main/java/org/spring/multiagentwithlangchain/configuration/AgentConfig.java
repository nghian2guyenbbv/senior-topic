package org.spring.multiagentwithlangchain.configuration;

import dev.langchain4j.agentic.AgenticServices;
import dev.langchain4j.model.chat.ChatModel;
import org.spring.multiagentwithlangchain.agent.OrderAgent;
import org.spring.multiagentwithlangchain.tool.OrderTool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {
  @Bean
  public OrderAgent orderAgent(ChatModel chatModel, OrderTool orderTool) {
  return AgenticServices.agentBuilder()
      .chatModel(c)
  }
}
