package com.example.freelecspringboot2webservice.web;

import com.example.freelecspringboot2webservice.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        /* mustache
        * 경로: src/main/resources/templates
        * 확장자: .mustache
        * */
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";  // src/main/resources/templates.mustache
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        System.out.println("GET: /posts/save");
        return "posts_save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        System.out.println("GET: /posts/update/{id}");
        model.addAttribute("post", postsService.findById(id));
        return "posts_update";
    }
}
