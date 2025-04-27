package com.talissonmelo.domain.service;

import com.talissonmelo.domain.response.ConsumerPostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class TextConsumerService {

    public void receivePost(ConsumerPostRequest request) {
        int words = countWords(request.body());
        BigDecimal value = new BigDecimal(words * 0.10);
        log.info("Consumer: {}", request.toString());
    }

    private int countWords(String body) {
        return (int) Arrays.stream(body.trim().split("\\s+")).count();
    }
}
