package com.azeem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

import com.azeem.entities.Role;

@Data
@NoArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private List<String> role;
	public UserDto(String username, String password, String firstname, String lastname, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		
	}
    
    
}
