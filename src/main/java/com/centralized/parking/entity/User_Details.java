package com.centralized.parking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User_Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @Column(name="UserName", unique = true, nullable = false)
    private String username;

    @Column(name="Password", nullable = false)
    private String password;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role_Details role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

	public Role_Details getRole() {
		return role;
	}

	public void setRole(Role_Details role) {
		this.role = role;
	}
    
}
