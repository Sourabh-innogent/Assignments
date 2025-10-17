package com.sourabh.studentcourse.service;

import com.sourabh.studentcourse.dto.CourseDTO;
import com.sourabh.studentcourse.model.Course;

import java.util.List;

public interface CourseService {
    Course addCourse(CourseDTO dto);
    Course updateInstructor(Course course);
    List<Object[]> getStudentCountByCourse();
    List<Course> getCoursesWithStudents();
    List<Object[]> getCourseDetailsWithStudentCount();
    List<Object[]> getTopNCourses(int n);
    List<Course> getCourses();
}
