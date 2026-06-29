package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class CXmlOrderRequest {

    @JacksonXmlProperty(localName = "OrderRequestHeader")
    private CXmlOrderRequestHeader orderRequestHeader;

    @JacksonXmlProperty(localName = "ItemOut")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CXmlItemOut> items;

    public CXmlOrderRequestHeader getOrderRequestHeader() { return orderRequestHeader; }
    public void setOrderRequestHeader(CXmlOrderRequestHeader orderRequestHeader) { this.orderRequestHeader = orderRequestHeader; }

    public List<CXmlItemOut> getItems() { return items; }
    public void setItems(List<CXmlItemOut> items) { this.items = items; }
}
