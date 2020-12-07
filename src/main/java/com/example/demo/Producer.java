package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final String TOPIC = "car_requests";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String movieRequestNotify(String brandRequest) {
        System.out.println(String.format("#### -> Producing brand request to notification service -> %s", brandRequest));
        this.kafkaTemplate.send(TOPIC, brandRequest);
        return "Successfully";
    }
}