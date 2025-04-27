package com.talissonmelo.domain.response;

import java.util.UUID;

public record ConsumerPostRequest(UUID id, String body) {
}
