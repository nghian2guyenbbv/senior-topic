package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CXmlItemDetail {

    @JacksonXmlProperty(localName = "UnitPrice")
    private CXmlMoneyWrapper unitPrice;

    @JacksonXmlProperty(localName = "Description")
    private String description;

    @JacksonXmlProperty(localName = "UnitOfMeasure")
    private String unitOfMeasure;

    public CXmlMoneyWrapper getUnitPrice() { return unitPrice; }
    public void setUnitPrice(CXmlMoneyWrapper unitPrice) { this.unitPrice = unitPrice; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUnitOfMeasure() { return unitOfMeasure; }
    public void setUnitOfMeasure(String unitOfMeasure) { this.unitOfMeasure = unitOfMeasure; }
}
