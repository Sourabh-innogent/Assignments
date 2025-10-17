package com.sourabh.studentcourse.service;

import com.sourabh.studentcourse.dto.StudentDTO;
import com.sourabh.studentcourse.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(StudentDTO dto);
    List<Student> getAllStudents();
    List<Student> getByCourseName(String courseName);
    List<Student> getStudentsWithoutCourse();
    List<Student> searchByCityAndInstructor(String city, String instructor);

}
