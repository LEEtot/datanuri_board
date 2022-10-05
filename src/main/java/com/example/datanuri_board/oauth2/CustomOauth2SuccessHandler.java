package com.example.datanuri_board.oauth2;

import com.example.datanuri_board.config.Security.TokenProvider;
import com.example.datanuri_board.dto.TokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomOauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final CustomOauth2UserService customOauth2UserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(authentication.getPrincipal(), Map.class);
        Map<String, String> attributes = objectMapper.convertValue(map.get("attributes"), Map.class);

        TokenDto email = tokenProvider.generateTokenDto(customOauth2UserService.getUserDataByEmail(attributes.get("email")));
        response.setContentType("text/html;charset=UTF-8");
        response.addHeader("grantType", email.getGrantType());
        response.addHeader("accessToken", email.getAccessToken());
        response.setContentType("application/json;charset=UTF-8");
    }
}
