package com.application.databaseapplication_v01.repository;

import com.application.databaseapplication_v01.entity.Course;
import com.application.databaseapplication_v01.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.department.dept_name = ?1")
    public Course getCourseByDepartmentName(String deptName);
}
