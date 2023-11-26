package com.azeem.config;

import com.azeem.entities.User;
import com.azeem.exception.ControllerException;
import com.azeem.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

import javax.naming.ContextNotEmptyException;

@Component
public class JwtUtil {

    @Autowired
    private CustomUserDetailService userDetailService;


    public String getUsernameFromToken(String token) throws Exception  {
    	String username =getClaimsFromToken(token, Claims::getSubject);
    	
    	return username;
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolver) throws Exception {
        final Claims claims =  getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) throws ControllerException {
    	try {
        return Jwts.parser().setSigningKey(JwtService.key).parseClaimsJws(token).getBody();
    	} catch(ExpiredJwtException ex) {
    		System.out.println(" INSIDE CATCH BLOCK ");
    		throw new ControllerException("Jwt Expired","401");
    		
    	}
    }

    public boolean validateToken(String token, UserDetails user) throws Exception {
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        final Date expirationDate  = getExpirationDateFromToken(token);
        return expirationDate.after(new Date());

    }


    public Date getExpirationDateFromToken(String token) {
       
			try {
				return getClaimsFromToken(token , Claims::getExpiration);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		
    }

}
