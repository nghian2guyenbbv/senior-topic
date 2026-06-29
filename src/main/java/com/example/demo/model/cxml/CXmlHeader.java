package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CXmlHeader {

    @JacksonXmlProperty(localName = "From")
    private CXmlParty from;

    @JacksonXmlProperty(localName = "To")
    private CXmlParty to;

    @JacksonXmlProperty(localName = "Sender")
    private CXmlSender sender;

    public CXmlParty getFrom() { return from; }
    public void setFrom(CXmlParty from) { this.from = from; }

    public CXmlParty getTo() { return to; }
    public void setTo(CXmlParty to) { this.to = to; }

    public CXmlSender getSender() { return sender; }
    public void setSender(CXmlSender sender) { this.sender = sender; }
}
