package com.application.databaseapplication_v01.service;

import com.application.databaseapplication_v01.entity.Course;
import com.application.databaseapplication_v01.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepo;

    public List<Course> courseListByDepartmentName(String deptName) {
        List<Course> courseList = courseRepo.findAll();
        for (Course c: courseList) {
            if (c.getDepartment().getDept_name() != deptName) {
                courseList.remove(c);
            }
        }
        return courseList;
    }

    public List<Course> courseList() {
        return courseRepo.findAll();
    }

    public Course get(Long id) {
        return courseRepo.getById(id);
    }

    public void save(Course course) {
        courseRepo.save(course);
    }
}
