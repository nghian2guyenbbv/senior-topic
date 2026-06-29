package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CXmlShipTo {

    @JacksonXmlProperty(localName = "Address")
    private CXmlAddress address;

    public CXmlAddress getAddress() { return address; }
    public void setAddress(CXmlAddress address) { this.address = address; }
}
