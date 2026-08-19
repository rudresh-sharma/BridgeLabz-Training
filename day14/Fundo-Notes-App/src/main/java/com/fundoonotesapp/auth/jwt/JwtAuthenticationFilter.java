package com.fundoonotesapp.auth.jwt;

import com.fundoonotesapp.security.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Get Authorization header
        String authHeader = request.getHeader("Authorization");

        // 2. Check whether JWT exists
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extract JWT
        String jwt = authHeader.substring(7);

        // 4. Extract email from JWT
        String userEmail = jwtService.extractUsername(jwt);

        // 5. Authenticate only if user is not already authenticated
        if (userEmail != null
                && SecurityContextHolder.getContext()
                        .getAuthentication() == null) {

            // 6. Load user from database
            UserDetails userDetails =
                    customUserDetailsService.loadUserByUsername(userEmail);

            // 7. Validate token
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // 8. Create authenticated object
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // 9. Add request details
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                // 10. Store authentication in SecurityContext
                SecurityContextHolder.getContext()
                        .setAuthentication(authenticationToken);
            }
        }

        // Continue with remaining filters
        filterChain.doFilter(request, response);
    }
}