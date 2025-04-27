package com.talissonmelo.rabbitmq.publisher.request;

import java.util.UUID;

public record PublisherPostRequest(UUID id, String body) {
}
