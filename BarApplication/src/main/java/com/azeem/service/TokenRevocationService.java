package com.azeem.service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class TokenRevocationService {

    private final Set<String> revokedTokens = ConcurrentHashMap.newKeySet();
	
    public void revokeToken(String token) {
    	revokedTokens.add(token);
    }
	
    public boolean isTokenRevoked(String token) {
    	return revokedTokens.contains(token);
    }
}
