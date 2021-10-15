package com.application.databaseapplication_v01.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "course_registration")
public class CourseRegistration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer seats_filled;

    private Integer max_seats;

    @DateTimeFormat(pattern = "hh:mma")
    private Time start_time;

    @DateTimeFormat(pattern = "hh:mma")
    private Time end_time;

    private boolean monday_wednesday_friday;

    private boolean tuesday_thursday;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_date;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "semester_id", referencedColumnName = "id")
    private Semester semester;

    @OneToMany(mappedBy = "courseRegistration")
    private Set<CourseCompleted> courseCompletedSet;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getSeats_filled() {
        return seats_filled;
    }

    public void setSeats_filled(Integer seats_filled) {
        this.seats_filled = seats_filled;
    }

    public Integer getMax_seats() {
        return max_seats;
    }

    public void setMax_seats(Integer max_seats) {
        this.max_seats = max_seats;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public boolean isMonday_wednesday_friday() {
        return monday_wednesday_friday;
    }

    public void setMonday_wednesday_friday(boolean monday_wednesday_friday) {
        this.monday_wednesday_friday = monday_wednesday_friday;
    }

    public boolean isTuesday_thursday() {
        return tuesday_thursday;
    }

    public void setTuesday_thursday(boolean tuesday_thursday) {
        this.tuesday_thursday = tuesday_thursday;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String weekdays() {
        if (isMonday_wednesday_friday())
            return "M-W-F";
        else
            return "T-Th";
    }

    public void incrementSeatsFilled() {
        if (this.seats_filled < this.max_seats) {
            this.seats_filled += 1;
        }
    }

    public void decrementSeatsFilled() {
        if (this.seats_filled > 0) {
            this.seats_filled -= 1;
        }
    }

    public Set<CourseCompleted> getCourseCompletedSet() {
        return courseCompletedSet;
    }

    public void setCourseCompletedSet(Set<CourseCompleted> courseCompletedSet) {
        this.courseCompletedSet = courseCompletedSet;
    }
}
