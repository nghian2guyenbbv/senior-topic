package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CXmlCredential {

    @JacksonXmlProperty(isAttribute = true)
    private String domain;

    @JacksonXmlProperty(localName = "Identity")
    private String identity;

    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }

    public String getIdentity() { return identity; }
    public void setIdentity(String identity) { this.identity = identity; }
}
