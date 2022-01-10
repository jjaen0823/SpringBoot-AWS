package com.example.freelecspringboot2webservice.web;

import com.example.freelecspringboot2webservice.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @RunWith(SpringRunner.class) -> @ExtendWith 변경 (@SpringBootTest 와 @WebMvcTest 안에 있음)
@WebMvcTest(controllers = HelloController.class,
    excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class HelloControllerTest {
    @Autowired
    private MockMvc mvc;

    @WithMockUser(roles = "USER")  // ROLE_USER 권한
    @Test
    void hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())  // mvc.perform 의 결과 검증, check 200 ok
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles = "USER")  // ROLE_USER 권한
    @Test
    void helloDto() throws Exception {
        String name = "hello";
        int amount = 10000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}