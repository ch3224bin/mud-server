package com.jeff.mud;

import com.jeff.mud.global.account.dao.AccountRepository;
import com.jeff.mud.global.security.JwtTokenProvider;
import com.jeff.mud.global.security.user.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@SpringBootApplication
public class MudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MudApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AccountRepository accountRepository;

	@GetMapping("/me")
	public Principal me (Principal user) {
		return user;
	}

	@PostMapping("/login")
	public String login(String username, String password) {
		CustomUserDetails customUserDetails = accountRepository.findByUsername(username)
				.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException(username));

		if (!passwordEncoder.matches(password, customUserDetails.getPassword())) {
			throw new IllegalArgumentException("잘못된 비밀번호입니다.");
		}
		return jwtTokenProvider.createToken(customUserDetails.getUsername(), customUserDetails.getAuthorities());
	}
}
