package com.sourabh.studentcourse.controller;

import com.sourabh.studentcourse.dto.CourseDTO;
import com.sourabh.studentcourse.model.Course;
import com.sourabh.studentcourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<Course> addCourse(@RequestBody CourseDTO dto) {
        return ResponseEntity.ok(courseService.addCourse(dto));
    }

    @PutMapping("/updateCourse")
    public ResponseEntity<Course> updateInstructor(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateInstructor( course));
    }

    @GetMapping("/allCourseWithStudent")
    public ResponseEntity<List<Course>> getAllCourseWithStudents() {
        return ResponseEntity.ok(courseService.getCourses());
    }


    @GetMapping("/courseWithStudentCount")
    public ResponseEntity<List<Object[]>> getCourseDetailsWithStudentCount() {
        return ResponseEntity.ok(courseService.getCourseDetailsWithStudentCount());
    }

    @GetMapping("/topCourses/{n}")
    public ResponseEntity<List<Object[]>> getTopCourses(@PathVariable int n) {
        return ResponseEntity.ok(courseService.getTopNCourses(n));
    }
}
