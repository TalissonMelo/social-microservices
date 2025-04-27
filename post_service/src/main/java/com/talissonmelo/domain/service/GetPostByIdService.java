package com.talissonmelo.domain.service;

import com.talissonmelo.api.response.PostResponse;
import com.talissonmelo.commom.PostMapper;
import com.talissonmelo.domain.Post;
import com.talissonmelo.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetPostByIdService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostResponse execute(UUID id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return postMapper.toPostResponse(post);
    }
}
