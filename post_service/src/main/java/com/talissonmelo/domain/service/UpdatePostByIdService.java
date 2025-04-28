package com.talissonmelo.domain.service;


import com.talissonmelo.domain.Post;
import com.talissonmelo.infrastructure.PostRepository;
import com.talissonmelo.rabbitmq.consumer.response.PublisherPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UpdatePostByIdService {

    private final PostRepository postRepository;

    public void execute(PublisherPostResponse publisher) {
        Post post = postRepository.findById(publisher.id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        post.update(publisher.wordCount(), publisher.calculatedValue());

        postRepository.saveAndFlush(post);
    }
}
