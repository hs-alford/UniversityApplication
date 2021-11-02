package com.application.databaseapplication_v01.entity;

import com.application.databaseapplication_v01.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String social;

    @Column(nullable = false, unique = true)
    private String student_id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, length = 45)
    private String grade_level;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(nullable = true, length = 80)
    private String major;

    @Column(nullable = true, length = 80)
    private String minor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /*@OneToMany(mappedBy = "student")
    private Set<CourseRegistration> courseRegistrations;*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_registration_id")
    )
    private Set<CourseRegistration> courseRegistrations = new HashSet<>();

    /*@OneToMany(mappedBy = "student")
    private Set<CourseCompleted> courseCompletedSet;*/

    public Student() {}

    public String getGrade_level() {
        return grade_level;
    }

    public void setGrade_level(String grade_level) {
        this.grade_level = grade_level;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public User getUser() {
        return user;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public boolean equals(User user) {
        return this.getUser().getId() == user.getId();
    }

    public Set<CourseRegistration> getCourseRegistrations() {
        return courseRegistrations;
    }

    public void setCourseRegistrations(Set<CourseRegistration> courseRegistrations) {
        this.courseRegistrations = courseRegistrations;
    }

    /*public String getFullName() {
        return this.firstName + " " + this.lastName;
    }*/

    /*public void setCourseCompletedSet(Set<CourseCompleted> courseCompletedSet) {
        this.courseCompletedSet = courseCompletedSet;
    }

    public Set<CourseCompleted> getCourseCompletedSet() {
        return courseCompletedSet;
    }*/


}
