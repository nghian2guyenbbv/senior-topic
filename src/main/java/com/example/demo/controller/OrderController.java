package com.example.demo.controller;

import com.example.demo.service.JsonXmlService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final JsonXmlService service;

    public OrderController(JsonXmlService service) {
        this.service = service;
    }

    @PostMapping(value = "/xml", consumes = "application/json", produces = "application/xml")
    public String toXml(@RequestBody String json) {
        return service.toXml(json);
    }

    @PostMapping(value = "/json", consumes = "application/xml", produces = "application/json")
    public String toJson(@RequestBody String xml) {
        return service.toJson(xml);
    }
}
