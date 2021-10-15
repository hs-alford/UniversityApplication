package com.application.databaseapplication_v01.repository;

import com.application.databaseapplication_v01.entity.Instructor;
import com.application.databaseapplication_v01.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {


}
