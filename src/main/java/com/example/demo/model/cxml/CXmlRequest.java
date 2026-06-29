package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CXmlRequest {

    @JacksonXmlProperty(isAttribute = true, localName = "deploymentMode")
    private String deploymentMode;

    @JacksonXmlProperty(localName = "OrderRequest")
    private CXmlOrderRequest orderRequest;

    public String getDeploymentMode() { return deploymentMode; }
    public void setDeploymentMode(String deploymentMode) { this.deploymentMode = deploymentMode; }

    public CXmlOrderRequest getOrderRequest() { return orderRequest; }
    public void setOrderRequest(CXmlOrderRequest orderRequest) { this.orderRequest = orderRequest; }
}
