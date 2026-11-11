package org.spring.multiagentwithlangchain.agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface OrderAgent {
  @UserMessage("""
      Customer request is: {{request}}
      Exact the order id from request
      User the available took to get the real details
      Return the complete order details only
      """)
  @Agent(outputKey = "orderDetails", description = "Fetchs Order from databse")
  public String checkOrder(@V("request") String request) ;
}
