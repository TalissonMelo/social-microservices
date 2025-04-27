package com.talissonmelo.api;

import com.talissonmelo.api.response.PostResponse;
import com.talissonmelo.domain.service.GetPostByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GetPostByIdController {

    private final GetPostByIdService service;

    @GetMapping(path = "/v1/api/posts/{postId}")
    public ResponseEntity<PostResponse> findById(@PathVariable UUID postId) {
        return ResponseEntity.ok(service.execute(postId));
    }
}
