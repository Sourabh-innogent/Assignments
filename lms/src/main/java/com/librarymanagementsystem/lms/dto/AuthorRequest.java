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
public class AuthorRequest {

    private Long id;

    private String author_name;

    private List<String> books;

}
