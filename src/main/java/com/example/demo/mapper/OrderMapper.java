package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.demo.model.json.OrderRequest;
import com.example.demo.model.xml.OrderXml;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", source = "orderId")
    @Mapping(target = "customer.name", source = "customerName")
    OrderXml toXml(OrderRequest request);
}
