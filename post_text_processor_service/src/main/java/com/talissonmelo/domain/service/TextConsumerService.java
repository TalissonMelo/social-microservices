package com.talissonmelo.domain.service;

import com.talissonmelo.domain.request.PublisherPostRequest;
import com.talissonmelo.domain.response.ConsumerPostRequest;
import com.talissonmelo.rabbitmq.RabbitConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class TextConsumerService {

    private final AmqpTemplate amqpTemplate;

    public void receivePost(ConsumerPostRequest request) {
        int words = countWords(request.body());
        BigDecimal value = new BigDecimal(words * 0.10);
        //log.info("Consumer: {}", request.toString());

        PublisherPostRequest publisher = new PublisherPostRequest(request.id(), words, value);
        amqpTemplate.convertAndSend(RabbitConfig.RESULT_QUEUE, publisher);
    }

    private int countWords(String body) {
        return (int) Arrays.stream(body.trim().split("\\s+")).count();
    }
}
