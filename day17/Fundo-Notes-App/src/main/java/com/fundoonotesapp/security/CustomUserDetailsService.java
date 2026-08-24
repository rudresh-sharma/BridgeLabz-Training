package com.fundoonotesapp.security;

import com.fundoonotesapp.user.entity.User;
import com.fundoonotesapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	private final UserCacheService userCacheService;

	@Override
	public UserDetails loadUserByUsername(String usernam) throws UsernameNotFoundException {

		String username = usernam.toLowerCase();

		// ==========================================
		// 1. CHECK REDIS
		// ==========================================

		CachedUserDetails cachedUser = userCacheService.getUser(username);

		if (cachedUser != null) {

			log.debug("User details found in Redis for email: {}", username);

			

			// returning original because become admin so might still as user in redis
			User user = userRepository.findByEmail(cachedUser.getUsername()).orElseThrow(
					() -> new UsernameNotFoundException("User not found with email: " + cachedUser.getUsername()));

			log.debug("User entity loaded from database for cached user: {}", username);
			return new CustomUserDetails(user);
		}

		// ==========================================
		// 2. REDIS CACHE MISS -> MYSQL
		// ==========================================

		log.debug("Redis cache miss. Loading user from database: {}", username);

		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

		// ==========================================
		// 3. CREATE CustomUserDetails
		// ==========================================

		CustomUserDetails userDetails = new CustomUserDetails(user);

		// ==========================================
		// 4. CREATE REDIS CACHE OBJECT
		// ==========================================

		CachedUserDetails userToCache = new CachedUserDetails(userDetails.getUsername(), userDetails.getPassword(),
				userDetails.getAuthorities().stream().map(authority -> authority.getAuthority()).toList(),
				userDetails.isEnabled());

		// ==========================================
		// 5. SAVE TO REDIS
		// ==========================================

		userCacheService.saveUser(userToCache, 3600);

		log.debug("User details saved to Redis: {}", username);
		return userDetails;
	}
}