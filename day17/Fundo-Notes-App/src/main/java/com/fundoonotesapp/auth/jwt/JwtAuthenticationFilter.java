package com.fundoonotesapp.auth.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fundoonotesapp.security.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final CustomUserDetailsService customUserDetailsService;
	private final TokenCacheService tokenCacheService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 1. Skip JWT validation for public endpoints
		if (isPublicEndpoint(request)) {
			filterChain.doFilter(request, response);
			return;
		}

		// 2. Get Authorization header
		String authHeader = request.getHeader("Authorization");

		log.debug("JWT authentication request: {}", request.getRequestURI());

		// 3. Reject protected request if token is missing
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			rejectUnauthorized(response);
			return;
		}

		// 4. Extract JWT
		String jwt = authHeader.substring(7);

		try {

			// 5. Extract email from JWT
			String userEmail = jwtService.extractUsername(jwt);

			log.debug("Username extracted from JWT: {}", userEmail);

			// 6. Check whether user is already authenticated
			if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				// 7. Check whether exact token exists in Redis
				boolean tokenCached = tokenCacheService.isTokenCached(userEmail, jwt);

//				log.debug("Token found in Redis cache: {}", tokenCached);

				if (!tokenCached) {
					rejectUnauthorized(response);
					return;
				}

				// 8. Load user details
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);

				// 9. Validate JWT
				if (!jwtService.isTokenValid(jwt, userDetails)) {
					rejectUnauthorized(response);
					return;
				}

				// 10. Create authenticated token
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				// 11. Add request details
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// 12. Store authenticated user
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

				log.debug("User authenticated successfully: {}", userEmail);			}

		} catch (Exception e) {

			// Invalid, expired, malformed JWT, etc.
			SecurityContextHolder.clearContext();

			System.out.println("JWT AUTHENTICATION FAILED: " + e.getMessage());

			rejectUnauthorized(response);
			return;
		}

		// 13. Continue only after successful authentication
		filterChain.doFilter(request, response);
	}

	private boolean isPublicEndpoint(HttpServletRequest request) {

		String path = request.getServletPath();

		return path.startsWith("/auth/") || path.startsWith("/swagger-ui/") || path.startsWith("/v3/api-docs")
				|| path.equals("/swagger-ui.html");
	}

	private void rejectUnauthorized(HttpServletResponse response) throws IOException {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing token");
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {

	    String path = request.getServletPath();

	    return path.startsWith("/auth/")
	            || path.equals("/swagger-ui.html")
	            || path.startsWith("/swagger-ui/")
	            || path.equals("/v3/api-docs")
	            || path.startsWith("/v3/api-docs/");
	}
}