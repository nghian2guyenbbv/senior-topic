package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Address {

    @JsonProperty("street")
    @JacksonXmlProperty(localName = "Street")
    private String street;

    @JsonProperty("city")
    @JacksonXmlProperty(localName = "City")
    private String city;

    @JsonProperty("state")
    @JacksonXmlProperty(localName = "State")
    private String state;

    @JsonProperty("zipCode")
    @JacksonXmlProperty(localName = "ZipCode")
    private String zipCode;

    @JsonProperty("country")
    @JacksonXmlProperty(localName = "Country")
    private String country;

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
