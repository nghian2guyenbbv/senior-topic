package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Customer {

    @JsonProperty("customerId")
    @JacksonXmlProperty(localName = "CustomerId")
    private String customerId;

    @JsonProperty("name")
    @JacksonXmlProperty(localName = "FullName")
    private String name;

    @JsonProperty("email")
    @JacksonXmlProperty(localName = "Email")
    private String email;

    @JsonProperty("address")
    @JacksonXmlProperty(localName = "ShippingAddress")
    private Address address;

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
}
