package com.talissonmelo.api;

import com.talissonmelo.api.response.PostSummaryResponse;
import com.talissonmelo.domain.service.GetPostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetPostsController {

    private final GetPostsService service;

    @GetMapping(path = "/v1/api/posts")
    public PagedModel<PostSummaryResponse> list(@PageableDefault Pageable pageable) {
        return service.execute(pageable);
    }
}
