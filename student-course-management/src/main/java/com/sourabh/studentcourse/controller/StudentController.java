package com.sourabh.studentcourse.controller;

import com.sourabh.studentcourse.dto.StudentDTO;
import com.sourabh.studentcourse.model.Student;
import com.sourabh.studentcourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody StudentDTO dto) {
        Student s = studentService.addStudent(dto);
        return ResponseEntity.ok(s);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/byCourse")
    public ResponseEntity<List<Student>> getByCourseName(@RequestParam(name = "courseName") String courseName) {
        return ResponseEntity.ok(studentService.getByCourseName(courseName));
    }

    @GetMapping("/noCourse")
    public ResponseEntity<List<Student>> getStudentsWithoutCourse() {
        return ResponseEntity.ok(studentService.getStudentsWithoutCourse());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchByCityAndInstructor(@RequestParam String city, @RequestParam String instructor) {
        return ResponseEntity.ok(studentService.searchByCityAndInstructor(city, instructor));
    }
}
