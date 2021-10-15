package com.application.databaseapplication_v01.controller;
import com.application.databaseapplication_v01.entity.*;
import com.application.databaseapplication_v01.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentservice;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private SemesterService semesterService;

    @Autowired DepartmentService departmentService;

    @Autowired
    private CourseRegistrationService courseRegistrationService;

    @GetMapping("/dashboard")
    public String showStudentDashboard() {
        return "student_dashboard";
    }

    @GetMapping("/dashboard/course_registration")
    String openStudentCourseRegistration(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Set<CourseRegistration> courses = courseRegistrationService.getCourseRegistrationsBySemester(semesterService.getActiveSemester());
        Set<CourseRegistration> offered_courses = courseRegistrationService.filterRegistrations(courses, currentUser);

        model.addAttribute("offered_courses", offered_courses);
        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);
        return "student_dashboard_course_registration";
    }


    @GetMapping("/dashboard/course_registration/process/{id}")
    public String processStudentCourseRegistration(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails currentUser, Model model) {
        CourseRegistration courseRegistration = courseRegistrationService.getCourseRegistrationById(id);
        Student student = userService.findByUsername(currentUser.getUsername()).getStudent();
        if (!studentservice.checkForTimeConflicts(student, courseRegistration)) {
            if (courseRegistrationService.registerForCourse(courseRegistration, currentUser)) {
                return "registration_success";
            }
        }
        String conflict = "Time Conflict!";
        model.addAttribute("conflict", conflict);
        //return "student_dashboard_course_registration";
        return openStudentCourseRegistration(model, currentUser);
    }



    @GetMapping("/dashboard/courses")
    public String openStudentCourses(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Student student = userService.findByUsername(currentUser.getUsername()).getStudent();
        Set<CourseRegistration> courseRegistrations = studentservice.studentSchedule(student);
        model.addAttribute("currentRegistrations", courseRegistrations);
        return "student_dashboard_courses";
    }

    @GetMapping("/dashboard/courses/unregister/{id}")
    public String unregisterStudentCourse(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails currentUser) {
        CourseRegistration courseRegistration = courseRegistrationService.getCourseRegistrationById(id);
        courseRegistrationService.unregisterForCourse(courseRegistration, currentUser);
        return "student_dashboard";
    }
}
