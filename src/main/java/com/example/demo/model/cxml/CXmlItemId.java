package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CXmlItemId {

    @JacksonXmlProperty(localName = "SupplierPartID")
    private String supplierPartId;

    public String getSupplierPartId() { return supplierPartId; }
    public void setSupplierPartId(String supplierPartId) { this.supplierPartId = supplierPartId; }
}
