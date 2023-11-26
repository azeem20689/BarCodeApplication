package com.azeem.service;

import com.azeem.entities.JwtRequest;
import com.azeem.entities.JwtResponse;
import com.azeem.entities.RefreshToken;
import com.azeem.entities.RefreshTokenRequest;
import com.azeem.entities.RefreshTokenResponse;
import com.azeem.entities.User;
import com.azeem.exception.ControllerException;
import com.azeem.repo.RefreshTokenRepo;
import com.azeem.repo.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {
	
	public static final String key = "afdadfafdafdafdgdsfgffrsrsfgyu54sfgfg545sfgsfge566454sfg";
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepo userRepo;
	@Value("${payment.status}")
	private boolean paymetnStatus;
	@Value("${token.expiry}")
	private int tokenExpiry;
	@Autowired
	private RefreshTokenRepo refreshTokenRepo;
	@Autowired
	private RefreshTokenService refreshTokenService;

	public ResponseEntity<?> generateToken(JwtRequest jwtRequest) {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		if (paymetnStatus == true) {
			if (auth.isAuthenticated()) {
				RefreshToken refreshToken =  refreshTokenService.createRefreshToken(jwtRequest.getUsername());
				String myToken = createToken(jwtRequest.getUsername());
				User user = userRepo.findById(jwtRequest.getUsername()).get();

				return new ResponseEntity<>(new JwtResponse(user, myToken,refreshToken.getToken()), HttpStatus.OK);

			} else if (!auth.isAuthenticated()) {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.PAYMENT_REQUIRED);
		}
		return null;
//        throw new ControllerException("603", "User credentials sent for token generation are invalid");
	}

	public String createToken(String username) {
		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * tokenExpiry ))
				.signWith(SignatureAlgorithm.HS256, getKey()).compact();

	}

	public Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(key);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public ResponseEntity<?> createRefreshToken(RefreshTokenRequest request){
		RefreshToken refreshToken = refreshTokenService.findByToken(request.getRefreshtoken());
		try {
		refreshTokenService.validateRefreshToken(refreshToken);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh Token expired Login again");
		}
		 String username = refreshTokenRepo.fetchByToken(refreshToken.getToken()).getUser().getUsername();
		 String rt = this.createToken(refreshToken.getUser().getUsername());
		  RefreshTokenResponse refreshTokenResponse = RefreshTokenResponse.builder()
				  .username(username)
				  .token(rt)
				  .build();
		return new ResponseEntity<>(refreshTokenResponse, HttpStatus.OK);
	}
}
