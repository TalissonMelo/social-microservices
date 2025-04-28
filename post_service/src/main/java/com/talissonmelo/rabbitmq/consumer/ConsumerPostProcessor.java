package com.talissonmelo.rabbitmq.consumer;

import com.talissonmelo.domain.service.UpdatePostByIdService;
import com.talissonmelo.rabbitmq.RabbitConfig;
import com.talissonmelo.rabbitmq.consumer.response.PublisherPostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumerPostProcessor {

    private final UpdatePostByIdService service;

    @RabbitListener(queues = RabbitConfig.RESULT_QUEUE)
    public void consumeProcessing(@Payload PublisherPostResponse result) {
        //log.info("Consumer Result queue: {}", result);
        service.execute(result);
    }

}
