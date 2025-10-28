package com.librarymanagementsystem.lms.controller;

import com.librarymanagementsystem.lms.dto.BooksRequest;
import com.librarymanagementsystem.lms.dto.BooksResponse;
import com.librarymanagementsystem.lms.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {
    @Autowired
    BookService service;

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody BooksRequest booksRequest) {
        service.add(booksRequest);
        return  ResponseEntity.ok("Book Added!!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<BooksResponse>> getAllBooks() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    @PatchMapping("/update")
    public ResponseEntity<BooksResponse> update( @RequestBody BooksRequest bookRequest ){
        return ResponseEntity.ok(service.updateBook(bookRequest));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteBookById(id);
    }
}
