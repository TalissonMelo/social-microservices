package com.talissonmelo.rabbitmq.consumer;

import com.talissonmelo.domain.response.ConsumerPostRequest;
import com.talissonmelo.domain.service.TextConsumerService;
import com.talissonmelo.rabbitmq.RabbitConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostConsumer {

    private final TextConsumerService service;

    @RabbitListener(queues = RabbitConfig.POST_QUEUE)
    public void consumeProcessing(@Payload ConsumerPostRequest request) {
        service.receivePost(request);
    }
}
