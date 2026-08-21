package com.fundoonotesapp.security;

import com.fundoonotesapp.user.entity.User;
import com.fundoonotesapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserCacheService userCacheService;


    @Override
    public UserDetails loadUserByUsername(String usernam)
            throws UsernameNotFoundException {

        String username = usernam.toLowerCase();


        // ==========================================
        // 1. CHECK REDIS
        // ==========================================

        CachedUserDetails cachedUser =
                userCacheService.getUser(username);

        if (cachedUser != null) {

            System.out.println(
                    "USER DETAILS FOUND IN REDIS: " + username
            );

            /*
             * Important:
             * Do NOT return cachedUser directly.
             *
             * AuthService and other parts of the application
             * expect CustomUserDetails.
             */

            User user = userRepository
                    .findByEmail(cachedUser.getUsername())
                    .orElseThrow(() ->
                            new UsernameNotFoundException(
                                    "User not found with email: "
                                            + cachedUser.getUsername()
                            )
                    );

            System.out.println(
                    "USER ENTITY LOADED FOR REDIS USER: "
                            + username
            );

            return new CustomUserDetails(user);
        }


        // ==========================================
        // 2. REDIS CACHE MISS -> MYSQL
        // ==========================================

        System.out.println(
                "REDIS CACHE MISS - LOADING USER FROM MYSQL: "
                        + username
        );

        User user = userRepository
                .findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email: "
                                        + username
                        )
                );


        // ==========================================
        // 3. CREATE CustomUserDetails
        // ==========================================

        CustomUserDetails userDetails =
                new CustomUserDetails(user);


        // ==========================================
        // 4. CREATE REDIS CACHE OBJECT
        // ==========================================

        CachedUserDetails userToCache =
                new CachedUserDetails(
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                                .stream()
                                .map(authority ->
                                        authority.getAuthority()
                                )
                                .toList(),
                        userDetails.isEnabled()
                );


        // ==========================================
        // 5. SAVE TO REDIS
        // ==========================================

        userCacheService.saveUser(
                userToCache,
                3600
        );

        System.out.println(
                "USER DETAILS SAVED TO REDIS: "
                        + username
        );


        return userDetails;
    }
}