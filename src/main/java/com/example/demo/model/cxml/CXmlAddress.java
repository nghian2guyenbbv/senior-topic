package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CXmlAddress {

    @JacksonXmlProperty(localName = "Name")
    private String name;

    @JacksonXmlProperty(localName = "PostalAddress")
    private CXmlPostalAddress postalAddress;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public CXmlPostalAddress getPostalAddress() { return postalAddress; }
    public void setPostalAddress(CXmlPostalAddress postalAddress) { this.postalAddress = postalAddress; }
}
