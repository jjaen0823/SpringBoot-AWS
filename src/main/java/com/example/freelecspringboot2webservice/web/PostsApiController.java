package com.example.freelecspringboot2webservice.web;

import com.example.freelecspringboot2webservice.service.posts.PostsService;
import com.example.freelecspringboot2webservice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

}
