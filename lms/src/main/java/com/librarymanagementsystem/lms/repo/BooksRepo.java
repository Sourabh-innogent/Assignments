package com.librarymanagementsystem.lms.repo;

import com.librarymanagementsystem.lms.model.Author;
import com.librarymanagementsystem.lms.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends JpaRepository<Books, Long> {
}
