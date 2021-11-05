package com.application.databaseapplication_v01.controller;

import com.application.databaseapplication_v01.entity.*;
import com.application.databaseapplication_v01.service.DepartmentService;
import com.application.databaseapplication_v01.service.InstructorService;
import com.application.databaseapplication_v01.service.StudentService;
import com.application.databaseapplication_v01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentservice;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "admin_users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.get(id);
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "admin_edit_user";
    }

    @GetMapping("/users/new")
    public String createNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", userService.listRoles());
        return "admin_edit_user";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {
        userService.save(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        List<Student> studentList = studentservice.studentList();
        model.addAttribute("studentList", studentList);
        return "admin_students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentservice.get(id);
        model.addAttribute("student", student);

        return "admin_edit_student";
    }

    @GetMapping("/students/new")
    public String createNewStudent(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "admin_edit_student";
    }

    @PostMapping("/students/save/student")
    public String saveStudent(Student student) {
        studentservice.save(student);

        return "redirect:/admin/students";
    }

    @GetMapping("/instructors")
    public String listInstructors(Model model) {
        List<Instructor> instructorList = instructorService.instructorList();
        model.addAttribute("instructorList", instructorList);
        return "admin_instructors";
    }

    @GetMapping("/instructors/edit/{id}")
    public String editInstructors(@PathVariable("id") Long id, Model model) {
        Instructor instructor = instructorService.get(id);
        model.addAttribute("instructor", instructor);
        List<Department> departments = departmentService.getAllDepartments();
        departments.remove(instructor.getDepartment());
        model.addAttribute("departments", departments);
        return "admin_edit_instructor";
    }

    @GetMapping("/instructors/new")
    public String createNewInstructor(Model model) {
        Instructor instructor = new Instructor();
        model.addAttribute("instructor", instructor);
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "admin_edit_instructor";
    }

    @PostMapping("/instructors/save/instructor")
    public String saveInstructor(Instructor instructor) {
        instructorService.save(instructor);

        return "redirect:/admin/instructors";
    }
}
