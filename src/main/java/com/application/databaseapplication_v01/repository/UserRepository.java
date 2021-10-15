package com.application.databaseapplication_v01.repository;

import com.application.databaseapplication_v01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	/*@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);*/

	@Query("SELECT u FROM User u WHERE u.username = ?1")
	public User findByUsername(String username);
}