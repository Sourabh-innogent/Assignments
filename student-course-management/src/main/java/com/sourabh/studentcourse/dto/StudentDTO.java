package com.sourabh.studentcourse.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private String name;
    private String email;
    private String city;
    private Long courseId;
}
