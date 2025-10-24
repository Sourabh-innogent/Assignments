package com.librarymanagementsystem.lms.controller;

import com.librarymanagementsystem.lms.dto.AuthorRequest;
import com.librarymanagementsystem.lms.dto.AuthorResponse;
import com.librarymanagementsystem.lms.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService service;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody AuthorRequest author) {
        service.addAuthor(author);
        return  ResponseEntity.ok("Author Added!!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        return ResponseEntity.ok(service.getAllAuthors());
    }

    @PutMapping("/update")
    public ResponseEntity<AuthorResponse> update( @RequestBody AuthorRequest authorRequest ){
        return ResponseEntity.ok(service.updateAuthor(authorRequest));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteAuthorById(id);
    }
}

