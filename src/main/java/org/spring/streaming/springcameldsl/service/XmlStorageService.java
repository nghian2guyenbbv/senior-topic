package org.spring.streaming.springcameldsl.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class XmlStorageService {

    private final Map<String, String> xmlStore = new ConcurrentHashMap<>();

    public void store(String orderId, String xml) {
        xmlStore.put(orderId, xml);
    }

    public String get(String orderId) {
        return xmlStore.get(orderId);
    }

    public Map<String, String> getAll() {
        return xmlStore;
    }

    public boolean exists(String orderId) {
        return xmlStore.containsKey(orderId);
    }
}
