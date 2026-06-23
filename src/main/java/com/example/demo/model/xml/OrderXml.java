package com.example.demo.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Order")
public class OrderXml {

    @JacksonXmlProperty(localName = "Id")
    private String id;

    @JacksonXmlProperty(localName = "Customer")
    private CustomerXml customer;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public CustomerXml getCustomer() { return customer; }
    public void setCustomer(CustomerXml customer) { this.customer = customer; }
}
