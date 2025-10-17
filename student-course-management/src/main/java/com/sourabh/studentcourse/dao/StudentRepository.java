package com.sourabh.studentcourse.dao;

import com.sourabh.studentcourse.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.course.courseName = :courseName")
    List<Student> findByCourseName(@Param("courseName") String courseName);

    @Query("SELECT s FROM Student s WHERE s.course IS NULL")
    List<Student> findStudentsWithoutCourse();

    @Query("SELECT s FROM Student s WHERE s.city = :city AND s.course.instructor = :instructor")
    List<Student> searchByCityAndInstructor(@Param("city") String city, @Param("instructor") String instructor);
}
