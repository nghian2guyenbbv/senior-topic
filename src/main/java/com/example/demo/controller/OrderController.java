package com.example.demo.controller;

import com.example.demo.model.json.OrderRequest;
import com.example.demo.service.JsonXmlService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final JsonXmlService service;

    public OrderController(JsonXmlService service) {
        this.service = service;
    }

    @PostMapping(value = "/xml", produces = "application/xml")
    public String convert(@RequestBody OrderRequest request) {
        return service.convert(request);
    }
}
