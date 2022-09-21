package com.example.springsecurityjwt;

import com.example.springsecurityjwt.model.entity.Role;
import com.example.springsecurityjwt.model.entity.User;
import com.example.springsecurityjwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "User 1", "user1", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "User 2", "user2", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "User 3", "user3", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "User 4", "user4", "1234", new ArrayList<>()));

            userService.addRoleToUser("user1", "ROLE_USER");
            userService.addRoleToUser("user2", "ROLE_MANAGER");
            userService.addRoleToUser("user3", "ROLE_ADMIN");
            userService.addRoleToUser("user4", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("user4", "ROLE_ADMIN");
            userService.addRoleToUser("user4", "ROLE_USER");
        };
    }
}
