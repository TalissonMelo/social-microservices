package com.talissonmelo.api.response;

import java.util.UUID;

public record PostSummaryResponse(UUID id, String title, String summary, String author) {
}
