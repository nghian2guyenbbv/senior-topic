package com.example.demo.route;

import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.xml.OrderXml;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jacksonxml.JacksonXMLDataFormat;
import org.springframework.stereotype.Component;

@Component
public class JsonToXmlRoute extends RouteBuilder {

    private final OrderMapper mapper;

    public JsonToXmlRoute(OrderMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void configure() {
        JacksonXMLDataFormat xml = new JacksonXMLDataFormat(OrderXml.class);

        from("direct:jsonToXml")
            .bean(mapper, "toXml")
            .marshal(xml);
    }
}
