package com.application.databaseapplication_v01.service;

import com.application.databaseapplication_v01.entity.Role;
import com.application.databaseapplication_v01.entity.Student;
import com.application.databaseapplication_v01.entity.User;
import com.application.databaseapplication_v01.repository.RoleRepository;
import com.application.databaseapplication_v01.repository.StudentRepository;
import com.application.databaseapplication_v01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired PasswordEncoder passwordEncoder;

	public void registerDefaultUser(User user) {
		//Role roleUser = roleRepo.findByName("User");
		//user.addRole(roleUser);
		encodePassword(user);
		userRepo.save(user);
	}

	public List<User> listAll() {
		return userRepo.findAll();
	}

	public User get(Long id) {
		return userRepo.findById(id).get();
	}

	public List<Role> listRoles() {
		return roleRepo.findAll();
	}

	public void save(User user) {
		encodePassword(user);
		userRepo.save(user);
	}

	public User findByUsername(String username) {
		return userRepo.findByUsername(username);

	}

	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}

}