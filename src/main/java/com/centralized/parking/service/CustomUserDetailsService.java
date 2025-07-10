package com.centralized.parking.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.centralized.parking.entity.Role_Details;
import com.centralized.parking.entity.User_Details;
import com.centralized.parking.model.Response;
import com.centralized.parking.model.UsersEntry;
import com.centralized.parking.repository.RolesRepository;
import com.centralized.parking.repository.UsersRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private PasswordEncoder passwordEncoder;

	private final UsersRepository userRepository;

	private final RolesRepository roleRepository;

	public CustomUserDetailsService(PasswordEncoder passwordEncoder, UsersRepository userRepository,
			RolesRepository roleRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User_Details user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		//org.springframework.security.core.UserDetails.User
		return new User(user.getUsername(),
						user.getPassword(),
						Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleCode())));
	}

	public Response createUser(UsersEntry request) {

		Response res = new Response();
		Role_Details role = new Role_Details();

		User_Details existingUser = userRepository.existsByUsername(request.getUsername());
		if (existingUser != null) {
			 res.setMessage("Username already exists");
			 return res;
		}

		User_Details user = new User_Details();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		role = roleRepository.findByCode(request.getRole());
		if (role == null) {
			throw new IllegalArgumentException("Invalid role: " + request.getRole());
		}
		user.setRole(role);
		userRepository.save(user);

		res.setMessage("User Added Successfully");
		return res;
	}

	public List<String> getAllRoles() {

		List<String> roles = null;
		roles = roleRepository.fetchAllRoles();
		return roles;

	}
}
