package com.jeff.mud;

import com.jeff.mud.global.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MudApplication.class, args);
	}

	@GetMapping("/me")
	public Principal me (Principal user) {
		return user;
	}
}
