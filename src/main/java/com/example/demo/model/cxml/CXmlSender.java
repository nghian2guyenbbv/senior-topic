package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CXmlSender {

    @JacksonXmlProperty(localName = "Credential")
    private CXmlCredential credential;

    @JacksonXmlProperty(localName = "UserAgent")
    private String userAgent;

    public CXmlCredential getCredential() { return credential; }
    public void setCredential(CXmlCredential credential) { this.credential = credential; }

    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
}
