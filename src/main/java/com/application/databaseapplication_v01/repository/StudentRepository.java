package com.application.databaseapplication_v01.repository;

import com.application.databaseapplication_v01.entity.Role;
import com.application.databaseapplication_v01.entity.Student;
import com.application.databaseapplication_v01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.birthdate = ?1 AND s.student_id = ?2 AND s.social = ?3")
    public Student findStudentByBirthdateAndAndStudent_idAndAndSocial(String student_id, String social, Date birthdate);

}
