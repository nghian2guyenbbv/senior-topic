package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CXmlItemOut {

    @JacksonXmlProperty(isAttribute = true)
    private int quantity;

    @JacksonXmlProperty(isAttribute = true, localName = "lineNumber")
    private int lineNumber;

    @JacksonXmlProperty(localName = "ItemID")
    private CXmlItemId itemId;

    @JacksonXmlProperty(localName = "ItemDetail")
    private CXmlItemDetail itemDetail;

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getLineNumber() { return lineNumber; }
    public void setLineNumber(int lineNumber) { this.lineNumber = lineNumber; }

    public CXmlItemId getItemId() { return itemId; }
    public void setItemId(CXmlItemId itemId) { this.itemId = itemId; }

    public CXmlItemDetail getItemDetail() { return itemDetail; }
    public void setItemDetail(CXmlItemDetail itemDetail) { this.itemDetail = itemDetail; }
}
