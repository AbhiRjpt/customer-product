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
        product.setDescription("Small 3-wheel scooter");
        product.setWeight(12.6);
        kafkaProducerService.sendMessage(product);
    }
}
