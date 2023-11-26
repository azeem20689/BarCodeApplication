package com.azeem.entities;

import com.azeem.repo.RoleRepo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserTable")
public class User {
	
    @Id
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name= "USER_ROLE" , joinColumns = @JoinColumn(name = "USER_ID")
    , inverseJoinColumns = @JoinColumn(name = "USER_ROLE")
    )
    private Set<Role> role;

	public User(String username, String password, String firstname, String lastname, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;	
	}

    
}
