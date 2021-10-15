package com.application.databaseapplication_v01.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "students_courses_completed")
@IdClass(CourseCompletedId.class)
public class CourseCompleted implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_registration_id", referencedColumnName = "id")
    private CourseRegistration courseRegistration;

    @Column(name = "final_grade")
    private Integer finalGrade;


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseRegistration getCourseRegistration() {
        return courseRegistration;
    }

    public void setCourseRegistration(CourseRegistration courseRegistration) {
        this.courseRegistration = courseRegistration;
    }

    public Integer getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Integer finalGrade) {
        this.finalGrade = finalGrade;
    }
}
