package com.example.chat.user.service;

import com.example.chat.user.entity.RefreshToken;
import com.example.chat.user.exception.RefreshTokenExpiredException;
import com.example.chat.user.repository.RefreshTokenRepository;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {
  private final RefreshTokenRepository refreshTokenRepository;

  public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
    this.refreshTokenRepository = refreshTokenRepository;
  }

  public void save(String token, Long userId, Instant exipryDate) {
    RefreshToken refreshToken = new RefreshToken(token, userId, exipryDate);
    refreshTokenRepository.save(refreshToken);
  }

  public void delete(String token) {
    refreshTokenRepository.deleteByToken(token);
  }

  public RefreshToken validate(String token) {
    RefreshToken refreshToken = refreshTokenRepository.getRefreshTokenByToken(token);
    if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
      refreshTokenRepository.delete(refreshToken);
      throw new RefreshTokenExpiredException();
    }
    return refreshToken;
  }
}
