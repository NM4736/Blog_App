package com.blog_Application;

import com.blog_Application.DAO.RoleRepo;
import com.blog_Application.Entity.Role;
import com.blog_Application.Service.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
public class BlogApplication implements CommandLineRunner {

	@Autowired
	RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
		System.out.println("###### Spring Boot Started ######");

	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		try
		{
			Role role1= new Role();
			role1.setId(Constants.ADMIN_USER);
			role1.setName("ROLE_ADMIN");


			Role role2= new Role();
			role2.setId(Constants.ADMIN_USER);
			role2.setName("ROLE_ADMIN");


			this.roleRepo.saveAll(List.of(role1,role2));


		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());

		}		System.out.println(passwordEncoder.encode("xyz"));
	}
}
