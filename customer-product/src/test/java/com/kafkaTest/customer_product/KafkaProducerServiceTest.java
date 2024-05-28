package com.kafkaTest.customer_product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class KafkaProducerServiceTest {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Test
    public void testSendMessage() {
        Product product = new Product();
        product.setId(126);
        product.setName("scooter10");
        HashMap<String, String> desc = new HashMap<>();
        desc.put("string", "Small 3-wheel scooter");
        product.setDescription(desc);
        HashMap<String, Double> weight = new HashMap<>();
        weight.put("string", 12.5);
        product.setWeight(weight);
        kafkaProducerService.sendMessage(product);
    }
}
