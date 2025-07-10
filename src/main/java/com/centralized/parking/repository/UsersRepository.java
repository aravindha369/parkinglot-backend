package com.centralized.parking.repository;

import com.centralized.parking.entity.User_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User_Details, Long> {
	
    Optional<User_Details> findByUsername(String username);
    
    @Query("select u from User_Details u where u.username = ?1")
    public User_Details existsByUsername(String username); 
}
