package com.centralized.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centralized.parking.entity.Role_Details;

public interface RolesRepository extends JpaRepository<Role_Details, Long>  {
	
	@Query("select r from Role_Details r where r.roleCode = ?1")
	public Role_Details findByCode (String roleCode);
	
	@Query("select r.roleCode from Role_Details r")
	public List<String> fetchAllRoles ();

}
