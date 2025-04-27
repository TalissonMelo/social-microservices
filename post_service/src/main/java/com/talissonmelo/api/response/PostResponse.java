package com.talissonmelo.api.response;

import java.math.BigDecimal;
import java.util.UUID;

public record PostResponse(UUID id, String title, String body, String author,
                           Integer wordCount, BigDecimal calculatedValue) {
}
