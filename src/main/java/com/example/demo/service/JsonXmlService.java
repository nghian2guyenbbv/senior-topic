package com.example.demo.service;

import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonXmlService {

    private final ProducerTemplate producerTemplate;

    public JsonXmlService(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public String toXml(String json) {
        return producerTemplate.requestBody("direct:jsonToXml", json, String.class);
    }

    public String toJson(String xml) {
        return producerTemplate.requestBody("direct:xmlToJson", xml, String.class);
    }

    public String toCXml(String json) {
        return producerTemplate.requestBody("direct:jsonToCXml", json, String.class);
    }

    public String fromCXml(String cxml) {
        return producerTemplate.requestBody("direct:cXmlToJson", cxml, String.class);
    }
}
