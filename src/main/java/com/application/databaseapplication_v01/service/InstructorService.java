package com.application.databaseapplication_v01.service;

import com.application.databaseapplication_v01.entity.Instructor;
import com.application.databaseapplication_v01.entity.Role;
import com.application.databaseapplication_v01.entity.Student;
import com.application.databaseapplication_v01.entity.User;
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
        instructorRepo.save(instructor);
        Role roleUser = roleRepo.findByName("ROLE_INSTRUCTOR");
        User user = instructor.getUser();
        user.addRole(roleUser);
    }

    public void save(Instructor instructor) {
        instructorRepo.save(instructor);
    }

    public List<Instructor> instructorList() { return instructorRepo.findAll(); }

    public Instructor get(Long id) { return instructorRepo.getById(id); }

}
