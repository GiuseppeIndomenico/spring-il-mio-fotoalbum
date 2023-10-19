package org.java.app.mvc.auth.config;

import org.java.app.mvc.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests()
	    .requestMatchers("/photos/**").hasAnyAuthority("SuperMegaDirettoreGalattico", "ADMIN")
	    .requestMatchers("/categories/**").hasAnyAuthority("SuperMegaDirettoreGalattico", "ADMIN")
	    .requestMatchers("/api/v1.0/**").permitAll()
	    .requestMatchers("/photos/create").hasAnyAuthority("SuperMegaDirettoreGalattico", "ADMIN")
	    .requestMatchers("/photos/update/**").hasAnyAuthority("SuperMegaDirettoreGalattico", "ADMIN")
	    .requestMatchers("/photos/delete/**").hasAnyAuthority("SuperMegaDirettoreGalattico", "ADMIN")
	    .requestMatchers("/ingredienti/create").hasAnyAuthority("SuperMegaDirettoreGalattico", "ADMIN")
	    .requestMatchers("/photos/offerta/**").hasAnyAuthority("SuperMegaDirettoreGalattico", "ADMIN")
	    .requestMatchers("/**").permitAll()
	    .and().formLogin().defaultSuccessUrl("/photos")
	    .and().logout();


		return http.build();
	}

	@Bean
	UserService userDetailsService() {
		return new UserService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
}
