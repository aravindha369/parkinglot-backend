package com.centralized.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centralized.parking.model.AuthRequest;
import com.centralized.parking.model.AuthResponse;
import com.centralized.parking.model.Response;
import com.centralized.parking.model.UsersEntry;
import com.centralized.parking.service.CustomUserDetailsService;
import com.centralized.parking.utility.JwtUtility;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/parking/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtility jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private CustomUserDetailsService customUserService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthResponse(jwt));
	}

	@PostMapping("/register")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> userEntry(@Valid @RequestBody UsersEntry request) {
		
		Response res = customUserService.createUser(request);
		return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
	}

	@GetMapping("/roleList")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getRolList() {
		
		List<String> roles = customUserService.getAllRoles();
		return new ResponseEntity<>(roles, HttpStatus.ACCEPTED);
	}
}
