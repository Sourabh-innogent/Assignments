package com.sourabh.studentcourse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String city;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;
}
