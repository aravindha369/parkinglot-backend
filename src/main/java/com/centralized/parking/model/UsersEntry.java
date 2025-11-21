package com.centralized.parking.model;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
public class UsersEntry {
	
	@NotBlank
	@Size(min=2, max=50)
	@Pattern(regexp = "^[A-Za-z0-9]+(?:\\s[A-Za-z0-9]+)*$", message = "Invalid Username Format")
	private String username;
	
	@NotBlank
	@Pattern(regexp = "^[A-Za-z0-9@#$%^&+=!]{7,}$",
			message = "Password:Minimum 7 characters.Only letters, numbers, and symbols (@#$%^&+=!) allowed")
    private String password;
    
	@NotBlank
    private String role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
