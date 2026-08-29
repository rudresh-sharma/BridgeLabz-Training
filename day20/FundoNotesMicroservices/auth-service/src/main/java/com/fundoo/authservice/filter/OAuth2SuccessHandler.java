package com.fundoo.authservice.filter;

import com.fundoo.authservice.dto.LoginResponse;

import com.fundoo.authservice.dto.OAuthUserRequest;
import com.fundoo.authservice.dto.UserAuthResponse;
import com.fundoo.authservice.client.UserClient;
import com.fundoo.authservice.entity.AuthProvider;
import com.fundoo.authservice.entity.RefreshToken;
import com.fundoo.authservice.service.JwtService;
import com.fundoo.authservice.service.RefreshTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserClient userClient;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        OAuthUserRequest oAuthUserRequest = new OAuthUserRequest(
                oAuth2User.getAttribute("name"),
                oAuth2User.getAttribute("email"),
                AuthProvider.GOOGLE,
                oAuth2User.getAttribute("picture")
        );

        UserAuthResponse user = userClient.findOrCreateOAuthUser(oAuthUserRequest);

        String accessToken = jwtService.generateToken(
                user.id(), user.email(), user.role()
        );

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user.id());

        LoginResponse loginResponse = new LoginResponse(
                accessToken, refreshToken.getToken(), "Bearer"
        );

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(
                objectMapper.writeValueAsString(loginResponse)
        );
    }
}