package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class OrderItem {

    @JsonProperty("itemId")
    @JacksonXmlProperty(localName = "ItemId")
    private String itemId;

    @JsonProperty("productName")
    @JacksonXmlProperty(localName = "ProductName")
    private String productName;

    @JsonProperty("quantity")
    @JacksonXmlProperty(localName = "Quantity")
    private int quantity;

    @JsonProperty("unitPrice")
    @JacksonXmlProperty(localName = "UnitPrice")
    private double unitPrice;

    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}
