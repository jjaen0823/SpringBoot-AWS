package com.example.freelecspringboot2webservice.web;

import com.example.freelecspringboot2webservice.config.auth.LoginUser;
import com.example.freelecspringboot2webservice.config.auth.dto.SessionUser;
import com.example.freelecspringboot2webservice.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {  //
        /* mustache
        * 경로: src/main/resources/templates
        * 확장자: .mustache
        * */
        model.addAttribute("posts", postsService.findAllDesc());

        // login 성공 시, OAuth2User loadUser 에서 SessionUser 저장 -> 로그인 성공 시 httpSession 에서 user 정보를 가져옴
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {  // Session 에 user 가 있을 때만 View 에 넘겨줌
            System.out.println(user);
            model.addAttribute("userName", user.getName());
        }

        return "index";  // src/main/resources/index.mustache
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
