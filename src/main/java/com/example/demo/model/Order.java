package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "Order")
public class Order {

    @JsonProperty("orderId")
    @JacksonXmlProperty(localName = "OrderId")
    private String orderId;

    @JsonProperty("orderDate")
    @JacksonXmlProperty(localName = "OrderDate")
    private String orderDate;

    @JsonProperty("status")
    @JacksonXmlProperty(localName = "Status")
    private String status;

    @JsonProperty("customer")
    @JacksonXmlProperty(localName = "Customer")
    private Customer customer;

    @JsonProperty("items")
    @JacksonXmlProperty(localName = "Item")
    @JacksonXmlElementWrapper(localName = "Items")
    private List<OrderItem> items;

    @JsonProperty("payment")
    @JacksonXmlProperty(localName = "Payment")
    private Payment payment;

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }
}
