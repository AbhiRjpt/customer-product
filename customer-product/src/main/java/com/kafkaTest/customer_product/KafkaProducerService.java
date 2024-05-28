package com.kafkaTest.customer_product;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class KafkaProducerService {
    private static final String TOPIC = "sql-server-docker.testDB.dbo.products";
    private final KafkaTemplate<String, Product> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Product> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Product product) {
        product.setId(127);
        product.setName("scooter11");
        product.setDescription("Small 4-wheel scooter");
        product.setWeight(9.5);
        kafkaTemplate.send(TOPIC, product);
    }
}
