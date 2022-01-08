package com.example.freelecspringboot2webservice.config.auth;

import com.example.freelecspringboot2webservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .headers().frameOptions().disable()
            .and()
                // URL 별 권한 관리의 시적점 : .authorizeRequests()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
            .and()
                .logout()
                .logoutSuccessUrl("/")
            .and()
                // oAuth2 login 기능에 대한 여러 설정의 진입점
                .oauth2Login()
                    // oAuth2 login 성공 이후, 사용자 정보를 가져올 때의 설정들을 담당함
                    .userInfoEndpoint()
                        // social login 성공 시, 후속 조치를 진행할 UserService Interface 구현체
                        .userService(customOAuth2UserService);  //
    }
}
