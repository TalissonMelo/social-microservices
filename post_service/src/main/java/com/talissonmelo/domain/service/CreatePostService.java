package com.talissonmelo.domain.service;

import com.talissonmelo.api.request.PostRequest;
import com.talissonmelo.api.response.PostResponse;
import com.talissonmelo.domain.Post;
import com.talissonmelo.infrastructure.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponse execute(PostRequest request) {

        Post post = Post.created(request.title(), request.body(), request.author());

        postRepository.saveAndFlush(post);

        return new PostResponse(post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getAuthor(),
                post.getWordCount(),
                post.getCalculatedValue());

    }
}
