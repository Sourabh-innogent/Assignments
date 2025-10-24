package com.librarymanagementsystem.lms.dto;

import com.librarymanagementsystem.lms.model.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BooksResponse {
    private String book_title;
    Integer stock;
    Author author;

}
