package com.application.databaseapplication_v01.service;

import com.application.databaseapplication_v01.entity.Instructor;
import com.application.databaseapplication_v01.entity.Role;
import com.application.databaseapplication_v01.entity.Student;
import com.application.databaseapplication_v01.repository.InstructorRepository;
import com.application.databaseapplication_v01.repository.RoleRepository;
import com.application.databaseapplication_v01.repository.StudentRepository;
import com.application.databaseapplication_v01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private InstructorRepository instructorRepo;

    public void registerInstructor(Instructor instructor) {
        //Role roleUser = roleRepo.findByName("INSTRUCTOR");
        //user.addRole(roleUser);
        //encodePassword(user);
        instructorRepo.save(instructor);
    }

    public void save(Instructor instructor) {
        //encodePassword(user);
        instructorRepo.save(instructor);
    }

    public List<Instructor> instructorList() { return instructorRepo.findAll(); }

}
