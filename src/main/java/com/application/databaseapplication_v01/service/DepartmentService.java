package com.application.databaseapplication_v01.service;

import com.application.databaseapplication_v01.entity.Department;
import com.application.databaseapplication_v01.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepo;

    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }
}
