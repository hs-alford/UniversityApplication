package com.application.databaseapplication_v01.service;

import com.application.databaseapplication_v01.entity.*;
import com.application.databaseapplication_v01.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private SemesterRepository semesterRepo;

    @Autowired
    private CourseRegistrationRepository courseRegistrationRepo;

    public void registerStudent(Student student) {
        studentRepo.save(student);

        Role roleUser = roleRepo.findByName("ROLE_STUDENT");
        User user = student.getUser();
        user.addRole(roleUser);
    }

    public void save(Student user) {
        //encodePassword(user);
        studentRepo.save(user);
    }

    public List<Student> studentList() { return studentRepo.findAll(); }

    public Student get(Long id) {
        return studentRepo.findById(id).get();
    }

    public Student validateStudent(String social, String student_id, Date birthday) {
        return studentRepo.findStudentByBirthdateAndAndStudent_idAndAndSocial(social, student_id, birthday);
    }

    public Set<CourseRegistration> studentSchedule(Student student) {
        Semester current = semesterRepo.getActiveSemester(1);
        Set<CourseRegistration> allRegistrations = student.getCourseRegistrations();
        for (CourseRegistration cr: allRegistrations) {
            if (cr.getSemester().getId() != current.getId()) {
                allRegistrations.remove(cr);
            }
        }
        return allRegistrations;
    }

    public boolean checkForTimeConflicts(Student student, CourseRegistration potentialCourse) {
        Set<CourseRegistration> schedule = studentSchedule(student);
        for (CourseRegistration cs: schedule) {
            LocalTime pc_Start, pc_End, cs_start, cs_end;
            pc_Start = potentialCourse.getStart_time().toLocalTime();
            pc_End = potentialCourse.getEnd_time().toLocalTime();
            cs_start = cs.getStart_time().toLocalTime();
            cs_end = cs.getEnd_time().toLocalTime();
            System.out.println("pcstart: " + pc_Start);
            System.out.println("pcsend: " + pc_End);
            System.out.println("csstart: " + cs_start);
            System.out.println("csend: " + cs_end);
            System.out.println("course getting registered: " + potentialCourse.getCourse().getName() + " mwf:" + potentialCourse.isMonday_wednesday_friday() + "    tth:" + potentialCourse.isTuesday_thursday());
            System.out.println(cs.getCourse().getName() + " mwf: " + cs.isMonday_wednesday_friday() + "     tth: " + cs.isTuesday_thursday());
            System.out.println();
            if (cs.isMonday_wednesday_friday() == potentialCourse.isMonday_wednesday_friday()) {
                if ((pc_Start.isAfter(cs_start) && pc_Start.isBefore(cs_end)) || (pc_End.isBefore(cs_end) && pc_End.isAfter(cs_start))) {
                    return true;
                }
            }
            else if (cs.isTuesday_thursday() == potentialCourse.isTuesday_thursday()) {
                if ((pc_Start.isAfter(cs_start) && pc_Start.isBefore(cs_end)) || (pc_End.isBefore(cs_end) && pc_End.isAfter(cs_start))) {
                    return true;
                }
            }
        }
        return false;
    }

}
