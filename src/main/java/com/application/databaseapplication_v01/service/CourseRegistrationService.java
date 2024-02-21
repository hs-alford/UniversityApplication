package com.application.databaseapplication_v01.service;

import com.application.databaseapplication_v01.entity.CourseRegistration;
import com.application.databaseapplication_v01.entity.Semester;
import com.application.databaseapplication_v01.entity.Student;
import com.application.databaseapplication_v01.repository.CourseRegistrationRepository;
import com.application.databaseapplication_v01.repository.SemesterRepository;
import com.application.databaseapplication_v01.repository.StudentRepository;
import com.application.databaseapplication_v01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class CourseRegistrationService {

    @Autowired
    private CourseRegistrationRepository courseRegistrationRepo;

    @Autowired
    private SemesterRepository semesterRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private UserRepository userRepo;

    public CourseRegistration getCourseRegistrationById(Long id) {
        return courseRegistrationRepo.getById(id);
    }

    public Set<CourseRegistration> getCourseRegistrationsBySemester(Long semester_id) {
        return courseRegistrationRepo.findCourseRegistrationBySemester(semester_id);
    }

    public Set<CourseRegistration> filterRegistrations(Set<CourseRegistration> courseRegistrationsSetAll, @AuthenticationPrincipal UserDetails currentUser) {
        Student student = userRepo.findByUsername(currentUser.getUsername()).getStudent();
        Set<CourseRegistration> currentRegistrations = student.getCourseRegistrations();
        for (CourseRegistration course: currentRegistrations) {
            if (courseRegistrationsSetAll.contains(course)) {
                courseRegistrationsSetAll.remove(course);
            }
        }
        return courseRegistrationsSetAll;
    }

    public boolean registerForCourse(CourseRegistration courseReg, @AuthenticationPrincipal UserDetails currentUser) {
        Student student = userRepo.findByUsername(currentUser.getUsername()).getStudent();
        Set<CourseRegistration> registrations = student.getCourseRegistrations();
        if (registrations.contains(courseReg)) {
            System.out.println("you have already registered for this course!");
            return false;
        }
        else {
            registrations.add(courseReg);
            courseReg.incrementSeatsFilled();
            return true;
        }
    }

    public void unregisterForCourse(CourseRegistration courseReg, @AuthenticationPrincipal UserDetails currentUser) {
        Student student = userRepo.findByUsername(currentUser.getUsername()).getStudent();
        Set<CourseRegistration> registrations = student.getCourseRegistrations();
        registrations.remove(courseReg);
        courseReg.decrementSeatsFilled();
    }

    public boolean checkConflictingTimes(CourseRegistration courseReg, Set<CourseRegistration> currentRegistrations) {
        for (CourseRegistration cs: currentRegistrations) {
            System.out.println("course getting registered: " + courseReg.getCourse() + " mwf:" + cs.isMonday_wednesday_friday() + "    tth:" + courseReg.isTuesday_thursday());
            System.out.println(cs.getCourse().getName() + " mwf? " + cs.isMonday_wednesday_friday() + "     tth? " + cs.isMonday_wednesday_friday());
            if ((courseReg.isTuesday_thursday() && cs.isTuesday_thursday()) || (courseReg.isMonday_wednesday_friday() && cs.isMonday_wednesday_friday())) {
                LocalTime courseRegEnd = courseReg.getEnd_time().toLocalTime();
                LocalTime courseRegStart = courseReg.getStart_time().toLocalTime();
                if ((courseRegEnd.isAfter(cs.getStart_time().toLocalTime())) || (courseRegStart.isBefore(cs.getEnd_time().toLocalTime()))) {
                    return false;
                }
            }
        }
        return true;
    }
}
