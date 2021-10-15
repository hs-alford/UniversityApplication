package com.application.databaseapplication_v01.service;

import com.application.databaseapplication_v01.entity.CourseRegistration;
import com.application.databaseapplication_v01.entity.Semester;
import com.application.databaseapplication_v01.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepo;

    public Long getActiveSemester() {
      Semester semester = semesterRepo.getActiveSemester(1);
      //System.out.println("the return value is = " + semester.getId());
      return semester.getId();
    }

}
