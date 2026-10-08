package com.learning.jpa.service;

import com.learning.jpa.dto.PostDto;
import com.learning.jpa.entity.Post;
import com.learning.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        // اینجا lazy loading کار می‌کنه چون توی تراکنشیم
        return new PostDto(
                post.getTitle(),
                post.getAuthor().getName(),   // lazy
                post.getComments().size()      // lazy
        );
    }
}