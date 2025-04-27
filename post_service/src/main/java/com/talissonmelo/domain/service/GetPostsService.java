package com.talissonmelo.domain.service;

import com.talissonmelo.api.response.PostSummaryResponse;
import com.talissonmelo.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetPostsService {

    private final PostRepository postRepository;

    public PagedModel<PostSummaryResponse> execute(Pageable pageable) {
        return new PagedModel<>(postRepository.findAll(pageable).map(post -> new PostSummaryResponse(
                post.getId(),
                post.getTitle(),
                summarize(post.getBody()),
                post.getAuthor()
        )));
    }

    private String summarize(String body) {
        return Arrays.stream(body.split("\\n")).limit(3).collect(Collectors.joining("\n"));
    }
}
