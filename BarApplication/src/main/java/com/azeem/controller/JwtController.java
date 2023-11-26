package com.azeem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.azeem.config.JwtUtil;
import com.azeem.dao.Dao;
import com.azeem.entities.JwtRequest;
import com.azeem.service.JwtService;
import com.azeem.service.RefreshTokenService;
import com.azeem.service.TokenRevocationService;


@RestController
@CrossOrigin(origins = "http://150.129.239.228:3000")
public class JwtController {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private RefreshTokenService refreshTokenService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private TokenRevocationService tokenRevocationService;
	@Autowired
	private Dao dao;

//    Generate new Token
	@PostMapping("/authenticate")
	public ResponseEntity<?> createToken(@RequestBody JwtRequest jwtRequest) {
		return jwtService.generateToken(jwtRequest);
	}

	@GetMapping("/logmeout")
	public ResponseEntity<?> logout(@RequestHeader("Authorization") String token){
		System.out.println("Token :"+token);
		tokenRevocationService.revokeToken(token.substring(7));
		dao.closeConnection();
		return ResponseEntity.ok("Token Revoked");
	}

	
	@GetMapping("/refreshtoken")
	public ResponseEntity<?> createRefreshToken(@RequestHeader("Authorization")String token) throws Exception{
	 String username=jwtUtil.getUsernameFromToken(token.replace("Bearer ", ""));
	 tokenRevocationService.revokeToken(token.substring(7));
	 System.out.println("USERNAME FROM TOKEN :"+username);
		return new ResponseEntity<>(jwtService.createToken(username),HttpStatus.OK);
	}
}
