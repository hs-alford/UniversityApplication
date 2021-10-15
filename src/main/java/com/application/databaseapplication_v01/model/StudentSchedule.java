package com.application.databaseapplication_v01.model;

import com.application.databaseapplication_v01.entity.CourseRegistration;
import com.application.databaseapplication_v01.entity.Semester;
import com.application.databaseapplication_v01.entity.Student;
import org.hibernate.internal.util.compare.CalendarComparator;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class StudentSchedule {

    private Student student;

    private Set<CourseRegistration> courseRegistrations;

    private Set<CourseRegistration> monday;

    private Set<CourseRegistration> tuesday;

    private Set<CourseRegistration> wednesday;

    private Set<CourseRegistration> thursday;

    private Set<CourseRegistration> friday;

    public StudentSchedule(Student student, Set<CourseRegistration> courseRegistrations) {
        this.student = student;
        this.courseRegistrations = courseRegistrations;
        this.monday = new HashSet<>();
        this.tuesday = new HashSet<>();
        this.wednesday = new HashSet<>();
        this.thursday = new HashSet<>();
        this.friday = new HashSet<>();

        for (CourseRegistration cr: this.courseRegistrations) {
            if (cr.isMonday_wednesday_friday()) {
                this.monday.add(cr);
                this.wednesday.add(cr);
                this.friday.add(cr);
            }
            if (cr.isTuesday_thursday()) {
                this.tuesday.add(cr);
                this.thursday.add(cr);
            }
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Set<CourseRegistration> getCourseRegistrations() {
        return courseRegistrations;
    }

    public void setCourseRegistrations(Set<CourseRegistration> courseRegistrations) {
        this.courseRegistrations = courseRegistrations;
    }

    public Set<CourseRegistration> getMonday() {
        return monday;
    }

    public void setMonday(Set<CourseRegistration> monday) {
        this.monday = monday;
    }

    public Set<CourseRegistration> getTuesday() {
        return tuesday;
    }

    public void setTuesday(Set<CourseRegistration> tuesday) {
        this.tuesday = tuesday;
    }

    public Set<CourseRegistration> getWednesday() {
        return wednesday;
    }

    public void setWednesday(Set<CourseRegistration> wednesday) {
        this.wednesday = wednesday;
    }

    public Set<CourseRegistration> getThursday() {
        return thursday;
    }

    public void setThursday(Set<CourseRegistration> thursday) {
        this.thursday = thursday;
    }

    public Set<CourseRegistration> getFriday() {
        return friday;
    }

    public void setFriday(Set<CourseRegistration> friday) {
        this.friday = friday;
    }



}
