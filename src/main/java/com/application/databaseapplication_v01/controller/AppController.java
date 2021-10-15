package com.application.databaseapplication_v01.controller;

import com.application.databaseapplication_v01.entity.Instructor;
import com.application.databaseapplication_v01.entity.Student;
import com.application.databaseapplication_v01.service.InstructorService;
import com.application.databaseapplication_v01.service.StudentService;
import com.application.databaseapplication_v01.service.UserService;
import com.application.databaseapplication_v01.entity.Role;
import com.application.databaseapplication_v01.entity.User;
import com.application.databaseapplication_v01.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {

	@Autowired
	private UserService userService;

	@Autowired
	private StudentService studentservice;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private ValidationService validationService;


	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("student", new Student());
		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user, Student student) {
		userService.registerDefaultUser(user);
		student.setUser(user);
		studentservice.registerStudent(student);
		return "student_dashboard";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);
		List<Student> listStudents = studentservice.studentList();
		model.addAttribute("studentList", listStudents);
		List<Instructor> instructorList = instructorService.instructorList();
		model.addAttribute("instructorList", instructorList);
		return "users";
	}

	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = userService.get(id);
		List<Role> listRoles = userService.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}

	@PostMapping("/users/save")
	public String saveUser(User user) {
		userService.save(user);

		return "redirect:/users";
	}

	@GetMapping("/users/edit/student/{id}")
	public String editStudent(@PathVariable("id") Long id, Model model) {
		Student student = studentservice.get(id);
		model.addAttribute("student", student);

		return "edit_student_form";
	}

	@PostMapping("/users/save/student")
	public String saveStudent(Student student) {
		studentservice.save(student);

		return "redirect:/users";
	}



}
