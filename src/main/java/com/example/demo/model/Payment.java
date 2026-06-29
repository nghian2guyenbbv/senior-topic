package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Payment {

    @JsonProperty("method")
    @JacksonXmlProperty(localName = "Method")
    private String method;

    @JsonProperty("cardLastFour")
    @JacksonXmlProperty(localName = "CardLastFour")
    private String cardLastFour;

    @JsonProperty("amount")
    @JacksonXmlProperty(localName = "Amount")
    private double amount;

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getCardLastFour() { return cardLastFour; }
    public void setCardLastFour(String cardLastFour) { this.cardLastFour = cardLastFour; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
