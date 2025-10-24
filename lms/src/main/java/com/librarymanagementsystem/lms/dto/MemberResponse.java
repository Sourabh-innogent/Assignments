package com.librarymanagementsystem.lms.dto;

import com.librarymanagementsystem.lms.model.Books;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberResponse {

    private String name;
    private String email;
    private List<Books> borrowedBooks;


}
