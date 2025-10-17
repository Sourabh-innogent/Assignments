package com.sourabh.studentcourse.dao;

import com.sourabh.studentcourse.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c.courseName, COUNT(s.id) FROM Course c LEFT JOIN c.students s GROUP BY c.courseName")
    List<Object[]> getStudentCountByCourse();

    @Query("SELECT c FROM Course c LEFT JOIN c.students s WHERE s IS NULL")
    List<Course> getCourses();

    @Query("SELECT c.courseName, c.instructor, COUNT(s.id) FROM Course c LEFT JOIN c.students s GROUP BY c.courseName, c.instructor")
    List<Object[]> getCourseDetailsWithStudentCount();

    @Query(value = "SELECT c.course_name, COUNT(s.id) as total FROM courses c LEFT JOIN students s ON c.id = s.course_id GROUP BY c.course_name ORDER BY total DESC LIMIT :n", nativeQuery = true)
    List<Object[]> getTopNCourses(@Param("n") int n);

}
