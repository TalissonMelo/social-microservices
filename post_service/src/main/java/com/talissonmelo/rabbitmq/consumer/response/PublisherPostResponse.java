package com.talissonmelo.rabbitmq.consumer.response;

import java.math.BigDecimal;
import java.util.UUID;

public record PublisherPostResponse(UUID id, Integer wordCount, BigDecimal calculatedValue) {
}
