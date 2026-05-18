package com.example.spring_with_react.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMessageView {
    private String value;
    private int partition;
    private long offset;
    private String receivedAt;
}
