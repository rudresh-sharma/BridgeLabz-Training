package com.fundoo.authservice.controller;

import com.fundoo.authservice.dto.LoginResponse;
import com.fundoo.authservice.dto.OAuthUserRequest;
import com.fundoo.authservice.entity.AuthProvider;
import com.fundoo.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class GoogleAuthController {

    private final AuthService authService;

    @GetMapping("/oauth2/success")
    public ResponseEntity<LoginResponse> googleLoginSuccess(
            @AuthenticationPrincipal OAuth2User oAuth2User) {

        OAuthUserRequest request = new OAuthUserRequest(
                oAuth2User.getAttribute("name"),
                oAuth2User.getAttribute("email"),
                AuthProvider.GOOGLE,
                oAuth2User.getAttribute("picture")
        );

        return ResponseEntity.ok(authService.loginWithGoogle(request));
    }
}