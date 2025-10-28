package com.librarymanagementsystem.lms.dto;
import com.librarymanagementsystem.lms.model.Author;
import com.librarymanagementsystem.lms.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BooksRequest {

    private Long id;

    private String book_title;

    Integer stock;

    Long author_id;

    private List<Member> members;

}

