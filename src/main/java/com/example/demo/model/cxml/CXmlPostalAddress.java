package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CXmlPostalAddress {

    @JacksonXmlProperty(localName = "Street")
    private String street;

    @JacksonXmlProperty(localName = "City")
    private String city;

    @JacksonXmlProperty(localName = "State")
    private String state;

    @JacksonXmlProperty(localName = "PostalCode")
    private String postalCode;

    @JacksonXmlProperty(localName = "Country")
    private String country;

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
