package com.application.databaseapplication_v01.controller;

import com.application.databaseapplication_v01.entity.*;
import com.application.databaseapplication_v01.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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

    @Autowired
    private CourseService courseService;

    @Autowired
    private SemesterService semesterService;

    @GetMapping("/dashboard")
    public String openAdminDashboard(Model model) {

        return "admin_dashboard";
    }

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

    @PostMapping("/users/save/user")
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

    @GetMapping("/courses")
    public String listCourses(Model model) {
        List<Course> courseList = courseService.courseList();
        model.addAttribute("courseList", courseList);
        return "admin_courses";
    }

    @GetMapping("/courses/edit/{id}")
    public String editCourses(@PathVariable("id") Long id, Model model) {
        Course course = courseService.get(id);
        model.addAttribute("course", course);
        List<Department> departments = departmentService.getAllDepartments();
        departments.remove(course.getDepartment());
        model.addAttribute("departments", departments);
        return "admin_edit_course";
    }

    @GetMapping("/courses/new")
    public String createNewCourse(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "admin_edit_course";
    }

    @PostMapping("/courses/save/course")
    public String saveCourse(Course course) {
        courseService.save(course);

        return "redirect:/admin/courses";
    }

    @GetMapping("/departments")
    public String listDepartments(Model model) {
        List<Department> departmentList = departmentService.getAllDepartments();
        model.addAttribute("departmentList", departmentList);
        return "admin_departments";
    }

    @GetMapping("/departments/edit/{id}")
    public String editDepartments(@PathVariable("id") Long id, Model model) {
        Department department = departmentService.get(id);
        model.addAttribute("department", department);


        return "admin_edit_department";
    }

    @GetMapping("/departments/new")
    public String createNewDepartment(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);

        return "admin_edit_department";
    }

    @PostMapping("/departments/save/department")
    public String saveDepartment(Department department) {
        departmentService.save(department);

        return "redirect:/admin/departments";
    }

    @GetMapping("/semesters")
    public String listSemesters(Model model) {
        List<Semester> semesterList = semesterService.getAllSemesters();
        model.addAttribute("semesterList", semesterList);

        return "admin_semesters";
    }

    @GetMapping("/semesters/edit/{id}")
    public String editSemesters(@PathVariable("id") Long id, Model model) {
        Semester semester = semesterService.get(id);
        model.addAttribute("semester", semester);
        ArrayList<String> semesterSessionNames = semesterService.semesterSessionNames();
        semesterSessionNames.remove(semester.getSemester());
        model.addAttribute("semesterSessionNames", semesterSessionNames);
        return "admin_edit_semester";
    }

    @GetMapping("/semesters/new")
    public String createNewSemester(Model model) {
        Semester semester = new Semester();
        model.addAttribute("semester", semester);
        model.addAttribute("semesterSessionNames", semesterService.semesterSessionNames());
        return "admin_edit_semester";
    }

    @PostMapping("/semesters/save/semester")
    public String saveSemester(Semester semester) {
        semesterService.save(semester);

        return "redirect:/admin/semesters";
    }
}
