package com.azeem.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azeem.entities.RefreshToken;
import com.azeem.repo.RefreshTokenRepo;
import com.azeem.repo.UserRepo;

@Service
public class RefreshTokenService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RefreshTokenRepo refreshTokenRepo;
	@Value("${refresh.token.expiry.millis}")
	private int refreshTokenExpiry;

	public RefreshToken createRefreshToken(String username) {
		RefreshToken existingToken = refreshTokenRepo.fetchByUsername(username);
		if (existingToken != null) {
			refreshTokenRepo.delete(existingToken);
		}
		RefreshToken refreshToken = RefreshToken.builder().user(userRepo.getByUsername(username))
				.expiryDate(Instant.now().plusMillis(refreshTokenExpiry))
				.token(UUID.randomUUID().toString()).build();
		refreshTokenRepo.save(refreshToken);
		return refreshToken;
	}
	
	public RefreshToken validateRefreshToken(RefreshToken token) {
		if(token.getExpiryDate().compareTo(Instant.now())<0) {
			refreshTokenRepo.delete(token);
			throw new RuntimeException("RefreshToken "+token.getToken()+" expired Login Again");
		}
		return token;
	}
	
	public RefreshToken findByToken(String token) {
		RefreshToken refreshToken =  refreshTokenRepo.fetchByToken(token);
		if(refreshToken !=null) {
			return refreshToken;
		}
		return null;
	}
	
	
}
