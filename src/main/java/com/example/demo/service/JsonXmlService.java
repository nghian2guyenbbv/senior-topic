package com.example.demo.service;

import com.example.demo.model.json.OrderRequest;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonXmlService {

    private final ProducerTemplate producerTemplate;

    public JsonXmlService(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    public String convert(OrderRequest request) {
        return producerTemplate.requestBody("direct:jsonToXml", request, String.class);
    }
}
