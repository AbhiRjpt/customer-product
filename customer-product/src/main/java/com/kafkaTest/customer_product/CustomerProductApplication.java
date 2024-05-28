package com.kafkaTest.customer_product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;

@SpringBootApplication(exclude = {ElasticsearchRestClientAutoConfiguration.class})
public class CustomerProductApplication {
	private static final Logger log = LoggerFactory.getLogger(CustomerProductApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CustomerProductApplication.class, args);
	}

}
