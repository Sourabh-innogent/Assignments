package com.librarymanagementsystem.lms.dao;

import com.librarymanagementsystem.lms.dto.BooksRequest;
import com.librarymanagementsystem.lms.dto.BooksResponse;
import com.librarymanagementsystem.lms.model.Books;
import com.librarymanagementsystem.lms.repo.BooksRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class BooksDao {
    @Autowired
    BooksRepo booksRepo;

    @Autowired
    private ModelMapper modelMapper;

    public void addBook(BooksRequest booksRequest) {
       booksRepo.save(modelMapper.map(booksRequest, Books.class));
    }

    public BooksResponse findBookById(Long id) {
        Books book = booksRepo.findById(id).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(book, BooksResponse.class);
    }

    public void deleteBookById( Long id  )  {
        if(booksRepo.findById(id).isPresent()) {
            booksRepo.deleteById(id);
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public BooksResponse updateBook(BooksRequest booksRequest)
    {
        Books book =  booksRepo.findById(booksRequest.getId()).orElseThrow(NoSuchElementException::new);
        if(book != null){
            Books updated = modelMapper.map(booksRequest, Books.class);
            booksRepo.save(updated);
            return modelMapper.map(updated, BooksResponse.class);
        }
        return null;
    }

    public List<BooksResponse> getAllBooks()
    {
        return booksRepo.findAll().stream().map(this::toDto).toList();
    }

    public BooksResponse toDto( Books books )
    {
        return modelMapper.map(books, BooksResponse.class);
    }
}
