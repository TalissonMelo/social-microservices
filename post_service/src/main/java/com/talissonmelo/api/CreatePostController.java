package com.talissonmelo.api;

import com.talissonmelo.api.request.PostRequest;
import com.talissonmelo.api.response.PostResponse;
import com.talissonmelo.domain.service.CreatePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreatePostController {

    private final CreatePostService createPostService;

    @PostMapping(path = "/v1/api/posts")
    public ResponseEntity<PostResponse> create(@RequestBody PostRequest request) {
        PostResponse response = createPostService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
