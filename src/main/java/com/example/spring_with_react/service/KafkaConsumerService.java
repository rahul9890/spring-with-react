package com.example.spring_with_react.service;

import com.example.spring_with_react.model.KafkaMessageView;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private static final int MAX_BUFFER = 100;

    private final Deque<KafkaMessageView> buffer = new ConcurrentLinkedDeque<>();

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord<String, String> record) {
        logger.info("Consumed message from topic '{}' partition={} offset={} key={}: {}",
                record.topic(), record.partition(), record.offset(), record.key(), record.value());
        KafkaMessageView view = new KafkaMessageView(
                record.value(),
                record.partition(),
                record.offset(),
                Instant.now().toString()
        );
        buffer.addFirst(view);
        while (buffer.size() > MAX_BUFFER) {
            buffer.pollLast();
        }
    }

    public List<KafkaMessageView> snapshot() {
        return new ArrayList<>(buffer);
    }
}
