package fi.ishtech.practice.oms.security;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import fi.ishtech.practice.oms.security.jwt.JwtFilter;
import fi.ishtech.practice.oms.security.userdetails.UserDetailsServiceImpl;

/**
 *
 * @author Muneer Ahmed Syed
 */
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());

		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// @formatter:off
 		http
 			.csrf(csrf -> csrf.disable())
 			.cors(corsConfigSource -> corsConfigurationSource())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(
					auth -> auth
						.requestMatchers(
								"/api/v1/auth/signup",
								"/api/v1/auth/signin",
								"/api/v1/auth/forgot-password",
								"/api/v1/auth/reset-password",
								"/error",
								"/",
								"/actuator/health",
								"/v3/api-docs",
								"/v3/api-docs/**",
								"/swagger-ui/**",
								"/swagger-ui.html",
								"/favicon.ico"
						)
							.permitAll()
						.anyRequest()
							.authenticated());
		// @formatter:on

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	/**
	 * Creates {@link CorsConfiguration}<br>
	 * Defaults are allowedOrigins = *, allowedHeaders = *, maxAge = 1800<br>
	 * You can use addAllowedOriginPattern to set http and/or https, subdomains etc.
	 *
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.applyPermitDefaultValues();
		corsConfig.setAllowedMethods(
				Arrays.stream(HttpMethod.values()).map(HttpMethod::name).collect(Collectors.toList()));

		UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();
		corsConfigSource.registerCorsConfiguration("/**", corsConfig);

		return corsConfigSource;
	}

}