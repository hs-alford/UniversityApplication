package com.application.databaseapplication_v01.repository;

import com.application.databaseapplication_v01.entity.Department;
import com.application.databaseapplication_v01.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
