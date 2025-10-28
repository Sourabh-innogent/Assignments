package com.librarymanagementsystem.lms.dao;

import com.librarymanagementsystem.lms.dto.BooksRequest;
import com.librarymanagementsystem.lms.dto.BooksResponse;
import com.librarymanagementsystem.lms.model.Author;
import com.librarymanagementsystem.lms.model.Books;
import com.librarymanagementsystem.lms.repo.AuthorRepo;
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
    AuthorRepo authorRepo;

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

    public BooksResponse updateBook(BooksRequest booksRequest) {
        Books book = booksRepo.findById(booksRequest.getId())
                .orElseThrow(() -> new NoSuchElementException("Book not found"));

        // Update only non-null or non-zero fields
        if (booksRequest.getBook_title() != null) {
            book.setBook_title(booksRequest.getBook_title());
        }

        if (booksRequest.getStock() != null && booksRequest.getStock() > 0) {
            book.setStock(booksRequest.getStock());
        }

        if ( booksRequest.getAuthor_id() != null) {
            Author author = authorRepo.findById( booksRequest.getAuthor_id())
                    .orElseThrow(() -> new NoSuchElementException("Author not found"));
            book.setAuthor(author);
        }

        if (booksRequest.getMembers() != null) {
            book.setMembers(booksRequest.getMembers());
        }

        Books saved = booksRepo.save(book);
        return modelMapper.map(saved, BooksResponse.class);
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
