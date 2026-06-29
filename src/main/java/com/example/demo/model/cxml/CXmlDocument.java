package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "cXML")
public class CXmlDocument {

    @JacksonXmlProperty(isAttribute = true, localName = "payloadID")
    private String payloadId;

    @JacksonXmlProperty(isAttribute = true)
    private String timestamp;

    @JacksonXmlProperty(isAttribute = true)
    private String version;

    @JacksonXmlProperty(localName = "Header")
    private CXmlHeader header;

    @JacksonXmlProperty(localName = "Request")
    private CXmlRequest request;

    public String getPayloadId() { return payloadId; }
    public void setPayloadId(String payloadId) { this.payloadId = payloadId; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public CXmlHeader getHeader() { return header; }
    public void setHeader(CXmlHeader header) { this.header = header; }

    public CXmlRequest getRequest() { return request; }
    public void setRequest(CXmlRequest request) { this.request = request; }
}
