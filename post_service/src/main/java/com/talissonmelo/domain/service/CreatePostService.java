package com.talissonmelo.domain.service;

import com.talissonmelo.api.request.PostRequest;
import com.talissonmelo.api.response.PostResponse;
import com.talissonmelo.commom.PostMapper;
import com.talissonmelo.domain.Post;
import com.talissonmelo.infrastructure.PostRepository;
import com.talissonmelo.rabbitmq.publisher.PublisherPostService;
import com.talissonmelo.rabbitmq.publisher.request.PublisherPostRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostService {

    private final PublisherPostService publisherPostService;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Transactional
    public PostResponse execute(PostRequest request) {

        Post post = Post.created(request.title(), request.body(), request.author());

        postRepository.saveAndFlush(post);

        publisherPostService.execute(new PublisherPostRequest(post.getId(), post.getBody()));

        return postMapper.toPostResponse(post);

    }
}
