package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CXmlParty {

    @JacksonXmlProperty(localName = "Credential")
    private CXmlCredential credential;

    public CXmlCredential getCredential() { return credential; }
    public void setCredential(CXmlCredential credential) { this.credential = credential; }
}
