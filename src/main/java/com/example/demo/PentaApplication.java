package com.example.demo;

import java.security.Principal;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AdminDataAccessService;
import com.example.demo.model.*;
import com.example.demo.service.*;
import com.example.demo.service.StudentService;

@SpringBootApplication
@RestController
public class PentaApplication {
	public static void main(String[] args) {
		SpringApplication.run(PentaApplication.class, args);

	}
	@GetMapping("/")
	public String greet(){
		return "Hello World";
	}
	@GetMapping("/principal")
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }
}
