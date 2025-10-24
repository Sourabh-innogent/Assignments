package com.librarymanagementsystem.lms.service;

import com.librarymanagementsystem.lms.dao.BooksDao;
import com.librarymanagementsystem.lms.dto.BooksRequest;
import com.librarymanagementsystem.lms.dto.BooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BooksDao booksDao;

    public void add(BooksRequest book){
        booksDao.addBook(book);
    }

    public BooksResponse findBookById(Long id){
        return booksDao.findBookById(id);
    }

    public void deleteBookById( Long id  ) {
        booksDao.deleteBookById(id);
    }

    public BooksResponse updateBook(BooksRequest booksRequest) {
        return booksDao.updateBook(booksRequest);
    }

    public List<BooksResponse> getAllBooks() {
        return booksDao.getAllBooks();
    }

}
