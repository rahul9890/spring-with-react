package com.example.spring_with_react.controller;

import com.example.spring_with_react.model.KafkaMessageView;
import com.example.spring_with_react.model.request.PublishMessageReq;
import com.example.spring_with_react.service.KafkaConsumerService;
import com.example.spring_with_react.service.KafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kafka")
@CrossOrigin
public class KafkaController {

    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    private final KafkaProducerService producerService;
    private final KafkaConsumerService consumerService;

    public KafkaController(@Autowired KafkaProducerService producerService,
                           @Autowired KafkaConsumerService consumerService) {
        this.producerService = producerService;
        this.consumerService = consumerService;
    }

    @PostMapping("/publish")
    public ResponseEntity<Void> publish(@RequestBody PublishMessageReq req) {
        logger.info("Received /publish request with message: {}", req.getMessage());
        producerService.publish(req.getMessage());
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/messages")
    public ResponseEntity<List<KafkaMessageView>> messages() {
        List<KafkaMessageView> snapshot = consumerService.snapshot();
        logger.info("Returning {} buffered message(s) from /messages", snapshot.size());
        return ResponseEntity.ok(snapshot);
    }
}
