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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	@GetMapping("/access-denied")
	public String openAccessDeniedPage(Model model)
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = dateFormat.format(new Date());
		model.addAttribute("currentDate", dateString);
		return "access-denied";
	}


}
