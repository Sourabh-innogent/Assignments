package com.sourabh.studentcourse.service.impl;

import com.sourabh.studentcourse.dao.CourseRepository;
import com.sourabh.studentcourse.dao.StudentRepository;
import com.sourabh.studentcourse.dto.StudentDTO;
import com.sourabh.studentcourse.model.Course;
import com.sourabh.studentcourse.model.Student;
import com.sourabh.studentcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Student addStudent(StudentDTO dto) {
        Student s = new Student();
        s.setName(dto.getName());
        s.setEmail(dto.getEmail());
        s.setCity(dto.getCity());
        if (dto.getCourseId() != null) {
            Optional<Course> course = courseRepository.findById(dto.getCourseId());
            course.ifPresent(s::setCourse);
        }
        return studentRepository.save(s);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getByCourseName(String courseName) {
        return studentRepository.findByCourseName(courseName);
    }

    @Override
    public List<Student> getStudentsWithoutCourse() {
        return studentRepository.findStudentsWithoutCourse();
    }

    @Override
    public List<Student> searchByCityAndInstructor(String city, String instructor) {
        return studentRepository.searchByCityAndInstructor(city, instructor);
    }



}
