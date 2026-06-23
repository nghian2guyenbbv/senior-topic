package org.spring.streaming.springcameldsl.route;

import org.apache.camel.builder.RouteBuilder;
import org.spring.streaming.springcameldsl.model.Order;
import org.spring.streaming.springcameldsl.service.XmlStorageService;
import org.springframework.stereotype.Component;

@Component
public class JsonToXmlRoute extends RouteBuilder {

    private final XmlStorageService xmlStorageService;

    public JsonToXmlRoute(XmlStorageService xmlStorageService) {
         this.xmlStorageService = xmlStorageService;
    }

    @Override
    public void configure() throws Exception {

         // REST endpoint: convert JSON to XML
         rest("/api")
             .post("/json-to-xml")
                 .consumes("application/json")
                 .produces("application/xml")
                 .type(Order.class)
                 .to("direct:convertJsonToXml")

             // REST endpoint: retrieve stored XML by orderId
             .get("/xml/{orderId}")
                 .produces("application/xml")
                 .to("direct:getStoredXml");

         // Route: convert Order POJO to XML and store it
         from("direct:convertJsonToXml")
             .routeId("json-to-xml-route")
             .log("Received order: ${body.orderId}")
             .setHeader("orderId", simple("${body.orderId}"))
             .marshal().jacksonXml(Order.class)
             .log("Converted to XML: ${body}")
             .bean(xmlStorageService, "store(${header.orderId}, ${body})")
             .log("Stored XML for order: ${header.orderId}");

         // Route: retrieve stored XML
         from("direct:getStoredXml")
             .routeId("get-stored-xml-route")
             .log("Retrieving XML for order: ${header.orderId}")
             .bean(xmlStorageService, "get(${header.orderId})");
    }
}
