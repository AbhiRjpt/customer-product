package com.kafkaTest.customer_product;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "simple_json.testDB.dbo.products", groupId = "sql-server-docker-schemahistory")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            String message = record.value();
            log.info("Received message: {}", message);

            // Validate JSON structure
            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode payload = jsonNode.path("payload").path("after");

            if (!payload.isMissingNode()) {
                Product product = objectMapper.treeToValue(payload, Product.class);
                log.info("product:{} ", product);

                String indexName = "product_data";
                log.info("IndecesAlias: {}", elasticsearchClient.indices().getAlias());
                boolean indexExists = elasticsearchClient.indices().exists(e -> e.index(indexName)).value();
                log.info("indexExists: {}",indexExists);
                if (!indexExists) {
                    // Create index if it does not exist
                    CreateIndexRequest createIndexRequest = CreateIndexRequest.of(c -> c.index(indexName));
                    CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(createIndexRequest);
                    log.info("Index creation response: {}", createIndexResponse.acknowledged());
                }
                IndexRequest<Object> indexRequest = IndexRequest.of(i -> i
                        .index(indexName)
                        .document(product)
                );
                IndexResponse indexResponse = elasticsearchClient.index(indexRequest);
                log.info("Document indexed with id: {}", indexResponse.id());
            } else {
                log.warn("No 'after' field in payload");
            }
        } catch (Exception e) {
            log.error("Error processing record", e);
        }
    }
}
