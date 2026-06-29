package com.example.demo.transformer;

import com.example.demo.model.*;
import com.example.demo.model.cxml.*;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderCXmlTransformer {

    private static final String CXML_VERSION = "1.2.014";
    private static final String CURRENCY = "USD";

    public CXmlDocument toCXml(Order order) {
        CXmlDocument doc = new CXmlDocument();
        doc.setPayloadId(order.getOrderId() + ".001@example.com");
        doc.setTimestamp(OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        doc.setVersion(CXML_VERSION);
        doc.setHeader(buildHeader(order));
        doc.setRequest(buildRequest(order));
        return doc;
    }

    public Order toOrder(CXmlDocument doc) {
        Order order = new Order();
        CXmlOrderRequestHeader reqHeader = doc.getRequest().getOrderRequest().getOrderRequestHeader();

        order.setOrderId(reqHeader.getOrderId());
        order.setOrderDate(reqHeader.getOrderDate());
        order.setStatus("PENDING");
        order.setCustomer(extractCustomer(doc));
        order.setItems(extractItems(doc.getRequest().getOrderRequest().getItems()));
        order.setPayment(extractPayment(reqHeader));
        return order;
    }

    // ── Build cXML from Order ─────────────────────────────────────────────────

    private CXmlHeader buildHeader(Order order) {
        CXmlHeader header = new CXmlHeader();
        header.setFrom(party("NetworkId", "buyer@example.com"));
        header.setTo(party("NetworkId", "supplier@example.com"));

        CXmlSender sender = new CXmlSender();
        sender.setCredential(credential("NetworkId", order.getCustomer().getCustomerId()));
        sender.setUserAgent("APA-Camel-Ex 1.0");
        header.setSender(sender);
        return header;
    }

    private CXmlRequest buildRequest(Order order) {
        CXmlRequest request = new CXmlRequest();
        request.setDeploymentMode("production");
        request.setOrderRequest(buildOrderRequest(order));
        return request;
    }

    private CXmlOrderRequest buildOrderRequest(Order order) {
        CXmlOrderRequest orderRequest = new CXmlOrderRequest();
        orderRequest.setOrderRequestHeader(buildOrderRequestHeader(order));
        orderRequest.setItems(buildItemOuts(order.getItems()));
        return orderRequest;
    }

    private CXmlOrderRequestHeader buildOrderRequestHeader(Order order) {
        CXmlOrderRequestHeader header = new CXmlOrderRequestHeader();
        header.setOrderId(order.getOrderId());
        header.setOrderDate(order.getOrderDate());
        header.setType("new");
        header.setTotal(moneyWrapper(CURRENCY, String.valueOf(order.getPayment().getAmount())));
        header.setShipTo(buildShipTo(order.getCustomer()));
        header.setContact(buildContact(order.getCustomer()));
        return header;
    }

    private CXmlShipTo buildShipTo(Customer customer) {
        Address a = customer.getAddress();
        CXmlPostalAddress postal = new CXmlPostalAddress();
        postal.setStreet(a.getStreet());
        postal.setCity(a.getCity());
        postal.setState(a.getState());
        postal.setPostalCode(a.getZipCode());
        postal.setCountry(a.getCountry());

        CXmlAddress addr = new CXmlAddress();
        addr.setName(customer.getName());
        addr.setPostalAddress(postal);

        CXmlShipTo shipTo = new CXmlShipTo();
        shipTo.setAddress(addr);
        return shipTo;
    }

    private CXmlContact buildContact(Customer customer) {
        CXmlContact contact = new CXmlContact();
        contact.setRole("endUser");
        contact.setName(customer.getName());
        contact.setEmail(customer.getEmail());
        return contact;
    }

    private List<CXmlItemOut> buildItemOuts(List<OrderItem> items) {
        List<CXmlItemOut> result = new ArrayList<>();
        int lineNumber = 1;
        for (OrderItem item : items) {
            CXmlItemId itemId = new CXmlItemId();
            itemId.setSupplierPartId(item.getItemId());

            CXmlItemDetail detail = new CXmlItemDetail();
            detail.setUnitPrice(moneyWrapper(CURRENCY, String.valueOf(item.getUnitPrice())));
            detail.setDescription(item.getProductName());
            detail.setUnitOfMeasure("EA");

            CXmlItemOut itemOut = new CXmlItemOut();
            itemOut.setQuantity(item.getQuantity());
            itemOut.setLineNumber(lineNumber++);
            itemOut.setItemId(itemId);
            itemOut.setItemDetail(detail);
            result.add(itemOut);
        }
        return result;
    }

    // ── Extract Order from cXML ───────────────────────────────────────────────

    private Customer extractCustomer(CXmlDocument doc) {
        Customer customer = new Customer();
        CXmlOrderRequestHeader reqHeader = doc.getRequest().getOrderRequest().getOrderRequestHeader();

        if (doc.getHeader() != null && doc.getHeader().getSender() != null) {
            customer.setCustomerId(doc.getHeader().getSender().getCredential().getIdentity());
        }
        if (reqHeader.getContact() != null) {
            customer.setName(reqHeader.getContact().getName());
            customer.setEmail(reqHeader.getContact().getEmail());
        }
        if (reqHeader.getShipTo() != null) {
            customer.setAddress(extractAddress(reqHeader.getShipTo().getAddress().getPostalAddress()));
        }
        return customer;
    }

    private Address extractAddress(CXmlPostalAddress postal) {
        Address addr = new Address();
        addr.setStreet(postal.getStreet());
        addr.setCity(postal.getCity());
        addr.setState(postal.getState());
        addr.setZipCode(postal.getPostalCode());
        addr.setCountry(postal.getCountry());
        return addr;
    }

    private List<OrderItem> extractItems(List<CXmlItemOut> cxmlItems) {
        List<OrderItem> items = new ArrayList<>();
        if (cxmlItems == null) return items;
        for (CXmlItemOut itemOut : cxmlItems) {
            OrderItem item = new OrderItem();
            item.setItemId(itemOut.getItemId().getSupplierPartId());
            item.setQuantity(itemOut.getQuantity());
            if (itemOut.getItemDetail() != null) {
                item.setProductName(itemOut.getItemDetail().getDescription());
                item.setUnitPrice(Double.parseDouble(
                        itemOut.getItemDetail().getUnitPrice().getMoney().getValue()));
            }
            items.add(item);
        }
        return items;
    }

    private Payment extractPayment(CXmlOrderRequestHeader reqHeader) {
        Payment payment = new Payment();
        payment.setMethod("CREDIT_CARD");
        if (reqHeader.getTotal() != null && reqHeader.getTotal().getMoney() != null) {
            payment.setAmount(Double.parseDouble(reqHeader.getTotal().getMoney().getValue()));
        }
        return payment;
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private CXmlParty party(String domain, String identity) {
        CXmlParty party = new CXmlParty();
        party.setCredential(credential(domain, identity));
        return party;
    }

    private CXmlCredential credential(String domain, String identity) {
        CXmlCredential cred = new CXmlCredential();
        cred.setDomain(domain);
        cred.setIdentity(identity);
        return cred;
    }

    private CXmlMoneyWrapper moneyWrapper(String currency, String value) {
        CXmlMoney money = new CXmlMoney();
        money.setCurrency(currency);
        money.setValue(value);
        CXmlMoneyWrapper wrapper = new CXmlMoneyWrapper();
        wrapper.setMoney(money);
        return wrapper;
    }
}
