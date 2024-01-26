package com.qp.groceryappapis;

import com.qp.groceryappapis.config.AppConstants;
import com.qp.groceryappapis.entity.Role;
import com.qp.groceryappapis.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class GroceryAppApisApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(GroceryAppApisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role role1 = new Role();
		role1.setId(AppConstants.ROLE_ADMIN);
		role1.setName("ROLE_ADMIN");
		Role role2 = new Role();
		role2.setId(AppConstants.ROLE_NORMAL);
		role2.setName("ROLE_NORMAL");
		roleRepository.saveAll(List.of(role1,role2));
	}
}
