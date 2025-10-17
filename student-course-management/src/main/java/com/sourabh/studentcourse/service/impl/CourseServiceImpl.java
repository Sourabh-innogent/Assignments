package com.sourabh.studentcourse.service.impl;

import com.sourabh.studentcourse.dao.CourseRepository;
import com.sourabh.studentcourse.dto.CourseDTO;
import com.sourabh.studentcourse.model.Course;
import com.sourabh.studentcourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course addCourse(CourseDTO dto) {
        Course c = new Course();
        c.setCourseName(dto.getCourseName());
        c.setInstructor(dto.getInstructor());
        return courseRepository.save(c);
    }

    @Override
    public Course updateInstructor(Course course) {
       Course existingcourse = courseRepository.findById(course.getId()).orElseThrow(()->new RuntimeException("course not found"));
        if (existingcourse != null) {
            existingcourse.setInstructor(course.getInstructor());
            existingcourse.setCourseName(course.getCourseName());
            return  courseRepository.save(existingcourse);
        }
        return null;
    }

    @Override
    public List<Object[]> getStudentCountByCourse() {
        return courseRepository.getStudentCountByCourse();
    }


    @Override
    public List<Object[]> getCourseDetailsWithStudentCount() {
        return courseRepository.getCourseDetailsWithStudentCount();
    }

    @Override
    public List<Course> getCoursesWithStudents() {
        return List.of();
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Object[]> getTopNCourses(int n) {
        return courseRepository.getTopNCourses(n);
    }
}
