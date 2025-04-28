package com.talissonmelo.domain.request;

import java.math.BigDecimal;
import java.util.UUID;

public record PublisherPostRequest(UUID id, Integer wordCount, BigDecimal calculatedValue) {
}
