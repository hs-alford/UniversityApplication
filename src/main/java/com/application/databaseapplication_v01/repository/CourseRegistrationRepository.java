package com.application.databaseapplication_v01.repository;

import com.application.databaseapplication_v01.entity.CourseRegistration;
import com.application.databaseapplication_v01.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {

    @Query("SELECT cr FROM CourseRegistration cr WHERE cr.semester.id = ?1")
    public Set<CourseRegistration> findCourseRegistrationBySemester(Long semester_id);

}
