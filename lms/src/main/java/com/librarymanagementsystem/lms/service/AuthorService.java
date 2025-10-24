package com.librarymanagementsystem.lms.service;

import com.librarymanagementsystem.lms.dao.AuthorDao;
import com.librarymanagementsystem.lms.dto.AuthorRequest;
import com.librarymanagementsystem.lms.dto.AuthorResponse;
import com.librarymanagementsystem.lms.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorDao authorDao;

    public void addAuthor(AuthorRequest author){
       authorDao.addAuthor(author);
    }

    public AuthorResponse findAuthorById(Long id){
        return authorDao.findAuthorById(id);
    }

    public void deleteAuthorById( Long id  ) {
        authorDao.deleteAuthorById(id);
    }

    public AuthorResponse updateAuthor(AuthorRequest authorRequest) {
        return authorDao.updateAuthor(authorRequest);
    }

    public List<AuthorResponse> getAllAuthors() {
         return authorDao.getAllAuthors();
    }

}
