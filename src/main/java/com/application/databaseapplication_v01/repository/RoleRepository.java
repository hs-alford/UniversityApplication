package com.application.databaseapplication_v01.repository;

import com.application.databaseapplication_v01.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query("SELECT r FROM Role r WHERE r.name = ?1")
	public Role findByName(String name);
}
