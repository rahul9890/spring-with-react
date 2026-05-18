package com.example.spring_with_react.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public KafkaProducerService(@Autowired KafkaTemplate<String, String> kafkaTemplate,
                                @Value("${app.kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publish(String message) {
        logger.info("Publishing message to topic '{}': {}", topic, message);
        kafkaTemplate.send(topic, message).whenComplete((result, ex) -> {
            if (ex != null) {
                logger.error("Failed to publish message to topic '{}': {}", topic, message, ex);
                return;
            }
            var md = result.getRecordMetadata();
            logger.info("Published message to topic '{}' partition={} offset={}: {}",
                    md.topic(), md.partition(), md.offset(), message);
        });
    }
}
