package com.example.freelecspringboot2webservice.web;

import com.example.freelecspringboot2webservice.service.posts.PostsService;
import com.example.freelecspringboot2webservice.web.dto.PostsResponseDto;
import com.example.freelecspringboot2webservice.web.dto.PostsSaveRequestDto;
import com.example.freelecspringboot2webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        System.out.println("POST: /api/v1/posts");
        return postsService.save(requestDto);
    }

    @GetMapping("/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @PutMapping("/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        System.out.println("PUT: /api/v1/posts/{id}");
        return postsService.update(id, requestDto);
    }
    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id) {
        System.out.println("DELETE: /api/v1/posts/{id}");
         postsService.delete(id);
         return id;
    }
}
