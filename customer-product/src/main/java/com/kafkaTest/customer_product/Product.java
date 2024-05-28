package com.kafkaTest.customer_product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private int id;
    private String name;
    private String description;
    private Double weight;
//    private ProductValue before;
//    private ProductValue after;
//    private Source source;
//    private String op;
//    private long ts_ms;
//    private String transaction;
}
