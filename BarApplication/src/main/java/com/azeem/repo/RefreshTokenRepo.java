package com.azeem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.azeem.entities.RefreshToken;
@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Integer> {

	@Query(value = "select * from refresh_token where token =:token",nativeQuery = true)
	RefreshToken fetchByToken(@Param("token")String token);
	
	@Query(value = "select * from refresh_token where user_username =:username",nativeQuery = true)
	RefreshToken fetchByUsername(@Param("username")String username);
}
