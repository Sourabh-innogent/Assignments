package com.librarymanagementsystem.lms.controller;

import com.librarymanagementsystem.lms.dao.LibraryDao;
import com.librarymanagementsystem.lms.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

        @PostMapping("/return/{memberId}/{bookId}")
        public ResponseEntity<String> returnBook(@PathVariable Long memberId, @PathVariable Long bookId) {
            try {
                service.returnBook(memberId, bookId);
                return ResponseEntity.ok("Book returned successfully!");
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body("Error: " + e.getMessage());
            }
    }
}
