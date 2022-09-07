package com.example.datanuri_board.config;

import com.example.datanuri_board.oauth2.CustomOauth2SuccessHandler;
import com.example.datanuri_board.oauth2.CustomOauth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Component
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final CustomOauth2UserService customOauth2UserService;
    private final CustomOauth2SuccessHandler customOauth2SuccessHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .authorizeRequests()

//                .antMatchers("/api/v1/user/login", "/api/v1/user/signup", "/*", "/board/**", "/boardsubject/**", "/resources/**", "/api/board/**", "/api/boardSubject/**").permitAll()

//                .antMatchers("/api/v1/user/login", "/api/v1/user/signup", "/", "/board/**", "/api/main", "/resources/**", "/api/board/**", "/api/boardSubject/**", "/oauth2/**", "/api/v1/user/login/google").permitAll()

                .antMatchers("/api/v1/user/login", "/api/v1/user/signup", "/api/v1/user/duplicateCheck", "/*", "/board/**", "/boardsubject/**","/user/**", "/resources/**", "/api/board/**", "/api/boardSubject/**").permitAll()

                //.antMatchers("/api/v1/user/login", "/api/v1/user/signup", "/", "/board/**", "/api/main", "/resources/**", "/api/board/**", "/api/boardSubject/**", "/oauth2/**", "/api/v1/user/login/google").permitAll()


                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider))

                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOauth2UserService)

                .and()
                .successHandler(customOauth2SuccessHandler);

        return http.build();
    }
}