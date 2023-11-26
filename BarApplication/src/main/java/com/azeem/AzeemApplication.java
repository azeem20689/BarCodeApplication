package com.azeem;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.azeem.dto.UserDto;
import com.azeem.entities.Role;
import com.azeem.entities.User;
import com.azeem.service.RoleService;
import com.azeem.service.UserService;
import java.util.*;

@SpringBootApplication
@EntityScan("com.azeem.entities")
@ComponentScan(basePackages = "com.azeem")
//@EnableJpaRepositories("com.azeem.repo")
@ComponentScan("com.azeem.repo")
public class AzeemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzeemApplication.class, args);
		System.out.println("Application Started !!");
	}
	
//	For saving a demo role and user in DB
	
//	@Bean
//	public CommandLineRunner loadData(UserService userService , RoleService roleService) {
//		return args->{
////			Role creation
//			Role adminRole = new Role("ROLE_ADMIN","Role for Admin");
//			Role userRole = new Role("ROLE_USER","Role for User");
//			roleService.saveRole(userRole);
//			roleService.saveRole(adminRole);
////			User Creation 
//			UserDto admin = new UserDto("demo","demo","demo","demo","demo@demo");
//			admin.setRole(List.of("ROLE_ADMIN","ROLE_USER"));
//			userService.saveUser(admin);
//		};
//	}

}
