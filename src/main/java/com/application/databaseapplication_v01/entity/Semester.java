package com.application.databaseapplication_v01.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.Set;

@Entity
@Table(name = "semester")
public class Semester implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "semester")
    private String semester;

    @Column(name = "active")
    private Integer active;

    @OneToMany(mappedBy = "semester")
    private Set<CourseRegistration> semester_courses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Set<CourseRegistration> getSemester_courses() {
        return semester_courses;
    }

    public void setSemester_courses(Set<CourseRegistration> semester_courses) {
        this.semester_courses = semester_courses;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getActive() {
        return active;
    }

    public Integer isActive() {
        return active;
    }


}
