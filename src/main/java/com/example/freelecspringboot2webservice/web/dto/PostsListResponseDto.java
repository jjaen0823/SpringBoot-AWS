package com.example.freelecspringboot2webservice.web.dto;

import com.example.freelecspringboot2webservice.domain.posts.Posts;

public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String modifiedDate;

    public PostsListResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.author = posts.getAuthor();
        this.modifiedDate = String.valueOf(posts.getModifiedDate());
    }
}
