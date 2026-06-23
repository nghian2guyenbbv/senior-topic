package com.example.demo.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CustomerXml {

    @JacksonXmlProperty(localName = "Name")
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
