package com.example.chat.user.repository;

import com.example.chat.user.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  RefreshToken getRefreshTokenByToken(String token);

  void deleteByToken(String token);
}
