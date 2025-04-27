package com.talissonmelo.commom;

import com.talissonmelo.api.response.PostResponse;
import com.talissonmelo.domain.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostResponse toPostResponse(Post post) {
        return new PostResponse(post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getAuthor(),
                post.getWordCount(),
                post.getCalculatedValue());
    }
}
