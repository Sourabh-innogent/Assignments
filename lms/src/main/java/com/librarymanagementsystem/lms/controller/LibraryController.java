package com.librarymanagementsystem.lms.controller;

import com.librarymanagementsystem.lms.dao.LibraryDao;
import com.librarymanagementsystem.lms.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService service;

    @PostMapping("/borrow/{memberId}/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long memberId, @PathVariable Long bookId) {
        try {
            service.borrowBook(memberId, bookId);
            return ResponseEntity.ok("Book borrowed successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
