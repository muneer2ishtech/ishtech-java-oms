package fi.ishtech.practice.oms.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fi.ishtech.practice.oms.payload.in.SigninRequest;
import fi.ishtech.practice.oms.payload.in.SignupRequest;
import fi.ishtech.practice.oms.payload.out.JwtResponse;
import fi.ishtech.practice.oms.security.jwt.JwtService;
import fi.ishtech.practice.oms.security.userdetails.UserDetailsImpl;
import fi.ishtech.practice.oms.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Muneer Ahmed Syed
 */
@RestController
@Slf4j
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private JwtService jwtService;

	@PostMapping(path = "/api/v1/auth/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
		log.debug("SignupRequest(email:{})", signupRequest.getEmail());

		// TODO: verify recaptcha

		Long userId = userProfileService.create(signupRequest);

		// TODO: send verification email

		// After signup, you can directly sign in the user,
		// but it is better let user sign in explicitly

		// @formatter:off
		URI uri = ServletUriComponentsBuilder
					.fromCurrentContextPath()
					.path("/api/v1/users/{userId}")
					.buildAndExpand(userId)
					.toUri();
		// @formatter:on

		return ResponseEntity.created(uri).body(userId);
	}

	@PostMapping(path = "/api/v1/auth/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JwtResponse> signin(@Valid @RequestBody SigninRequest signinRequest) {
		log.debug("Sigin request for {}", signinRequest.getEmail());

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return ResponseEntity.ok(jwtService.generateJwtResponse((UserDetailsImpl) authentication.getPrincipal()));
	}

}