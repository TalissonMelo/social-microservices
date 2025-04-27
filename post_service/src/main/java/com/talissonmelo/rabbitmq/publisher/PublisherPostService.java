package com.talissonmelo.rabbitmq.publisher;

import com.talissonmelo.rabbitmq.RabbitConfig;
import com.talissonmelo.rabbitmq.publisher.request.PublisherPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherPostService {

    private final AmqpTemplate amqpTemplate;

    public void execute(PublisherPostRequest publisherPostRequest) {
        amqpTemplate.convertAndSend(RabbitConfig.POST_QUEUE, publisherPostRequest);
    }
}
