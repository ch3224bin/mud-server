package com.jeff.mud.global.security;

import com.jeff.mud.global.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.jeff.mud.global.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.jeff.mud.global.security.oauth2.OAuth2AuthenticationSuccessHandler;
import com.jeff.mud.global.security.user.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;
	private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

	public WebSecurityConfig(CustomOAuth2UserService customOAuth2UserService,
													 OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler,
													 OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler) {
		this.customOAuth2UserService = customOAuth2UserService;
		this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
		this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
			.formLogin().disable()
			.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.antMatchers("/login/**").permitAll()
				.antMatchers("/auth/**", "/oauth2/**").permitAll()
				.mvcMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.cors()
				.and()
			.oauth2Login()
				.authorizationEndpoint()
					.baseUri("/oauth2/authorize")
					.authorizationRequestRepository(new HttpCookieOAuth2AuthorizationRequestRepository())
					.and()
				.userInfoEndpoint()
					.userService(customOAuth2UserService)
					.and()
				.successHandler(oAuth2AuthenticationSuccessHandler)
				.failureHandler(oAuth2AuthenticationFailureHandler);

		http
			.csrf().disable();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOrigin("http://localhost:3000");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder encoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
