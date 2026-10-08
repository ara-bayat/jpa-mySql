package com.learning.jpa.controller;

import com.learning.jpa.entity.Post;
import com.learning.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;

    @GetMapping(path = "/")
    public ResponseEntity<Page<Post>> findByAuthorName(@RequestParam String name) {
        Sort sort = Sort.by(
                Sort.Order.asc("createdAt"),      // فیلد اول: صعودی
                Sort.Order.desc("title")      // فیلد دوم: نزولی
        );
        Pageable pageable = PageRequest.of(0, 10, sort);
        return ResponseEntity.ok(postRepository.findByAuthorName(name,pageable));
    }


}
