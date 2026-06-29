package com.example.demo.model.cxml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CXmlOrderRequestHeader {

    @JacksonXmlProperty(isAttribute = true, localName = "orderID")
    private String orderId;

    @JacksonXmlProperty(isAttribute = true, localName = "orderDate")
    private String orderDate;

    @JacksonXmlProperty(isAttribute = true)
    private String type;

    @JacksonXmlProperty(localName = "Total")
    private CXmlMoneyWrapper total;

    @JacksonXmlProperty(localName = "ShipTo")
    private CXmlShipTo shipTo;

    @JacksonXmlProperty(localName = "Contact")
    private CXmlContact contact;

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public CXmlMoneyWrapper getTotal() { return total; }
    public void setTotal(CXmlMoneyWrapper total) { this.total = total; }

    public CXmlShipTo getShipTo() { return shipTo; }
    public void setShipTo(CXmlShipTo shipTo) { this.shipTo = shipTo; }

    public CXmlContact getContact() { return contact; }
    public void setContact(CXmlContact contact) { this.contact = contact; }
}
