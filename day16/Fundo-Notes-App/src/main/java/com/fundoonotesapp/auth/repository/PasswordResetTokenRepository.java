package com.fundoonotesapp.auth.repository;

import com.fundoonotesapp.auth.entity.PasswordResetToken;
import com.fundoonotesapp.user.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository
        extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);

    void deleteByUser(User user);
}