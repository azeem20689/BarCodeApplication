package com.azeem.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.azeem.service.TokenRevocationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogoutFilter extends OncePerRequestFilter {
	@Autowired
	private TokenRevocationService tokenRevocationService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailService userDetailService;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("Request :"+request);
		
        String header = request.getHeader("Authorization");
        if(header!=null) {
		System.out.println("Header received : =====:"+header);
		String token = header.substring(7);	
			if (tokenRevocationService.isTokenRevoked(token)) {
				System.out.println("Token UNAUTHORIZING.....");
//				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				try {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            response.getWriter().write("Unauthorized: Invalid or expired token");
	            response.getWriter().flush();
	            return;
				}catch(IllegalStateException ae) {
					ae.printStackTrace();
				}
				
			}else {
				System.out.println("Token NOT UNAUTHORIZING.....");
			}
        }
	        filterChain.doFilter(request,  response);
	}

}
