package com.application.databaseapplication_v01.service;

import com.application.databaseapplication_v01.entity.Instructor;
import com.application.databaseapplication_v01.entity.Student;
import com.application.databaseapplication_v01.entity.User;
import com.application.databaseapplication_v01.repository.RoleRepository;
import com.application.databaseapplication_v01.repository.StudentRepository;
import com.application.databaseapplication_v01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.util.List;

@Service
public class ValidationService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private StudentRepository studentRepo;

    public String checkStudentExist(List<Student> students, User user) {
        for (Student stu: students) {
            if (stu.getUser().getId().equals(user.getId())) {
                return "Student already exits for the currently signed in account.";
            }
        }
        return "";
    }



}
