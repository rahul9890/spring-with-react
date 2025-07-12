package com.example.spring_with_react.configs;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.spring_with_react.utils.CommonConstants.CORRELATION_ID;

public class OrderedMDCConverter extends ClassicConverter {

    public static final List<String> DEFAULT_ORDERED_FIELDS = Arrays.asList(CORRELATION_ID, "CustomerId");
    private final String ipAddress;

    public OrderedMDCConverter() {
        String localIpAddress;
        try {
            localIpAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException unknownHostException) {
            localIpAddress = null;
        }
        this.ipAddress = localIpAddress;
    }

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        Map<String, String> mdcMap = iLoggingEvent.getMDCPropertyMap();
        StringJoiner joiner = new StringJoiner(", ");

        if (ipAddress != null) {
            joiner.add("ip=" + ipAddress);
        }

        this.appendOrderedFields(joiner, mdcMap);
        this.appendUnorderedFields(joiner, mdcMap);

        return joiner.toString();
    }

    private void appendOrderedFields(StringJoiner joiner, Map<String, String> mdcMap) {
        for (String field : DEFAULT_ORDERED_FIELDS) {
            String value = mdcMap.get(field);
            if (value != null && !value.trim().isEmpty()) {
                joiner.add(field.trim() + "=" + value.trim());
            }
        }
    }

    private void appendUnorderedFields(StringJoiner joiner, Map<String, String> mdcMap) {
        Set<String> orderedFieldSet = new HashSet<>(DEFAULT_ORDERED_FIELDS);
        for (Map.Entry<String, String> entry : mdcMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!orderedFieldSet.contains(key) && value != null && !value.trim().isEmpty()) {
                joiner.add(key.trim() + "=" + value.trim());
            }
        }
    }
}
