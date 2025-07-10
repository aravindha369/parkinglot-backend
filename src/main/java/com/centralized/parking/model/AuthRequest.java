package com.centralized.parking.model;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
public class AuthRequest {
	
	@NotBlank
	@Size(min=2, max=50)
	@Pattern(regexp = "^[A-Z][a-z]*(\\s[A-Z][a-z]*)*$", message = "Invalid Username")
	private String username;
	
	@NotBlank
	@Pattern(regexp = "^[A-Za-z0-9@#$%^&+=!]{7,}$",
			message = "Invalid Password")
    private String password;

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

}
