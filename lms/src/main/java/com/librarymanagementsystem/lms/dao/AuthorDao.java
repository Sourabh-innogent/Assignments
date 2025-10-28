package com.librarymanagementsystem.lms.dao;


import com.librarymanagementsystem.lms.dto.AuthorRequest;
import com.librarymanagementsystem.lms.dto.AuthorResponse;
import com.librarymanagementsystem.lms.dto.BooksRequest;
import com.librarymanagementsystem.lms.model.Author;
import com.librarymanagementsystem.lms.repo.AuthorRepo;
import com.librarymanagementsystem.lms.repo.BooksRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class AuthorDao {

    @Autowired
    AuthorRepo authorRepo;

    @Autowired
    BooksRepo booksRepo;

    @Autowired
    private ModelMapper modelMapper;

    public void addAuthor(AuthorRequest authorRequest) {
        Author author = new Author();

        author.setAuthor_name(authorRequest.getAuthor_name());

        if (authorRequest.getBooks() != null && !authorRequest.getBooks().isEmpty()) {
            var bookEntities = booksRepo.findAllById(authorRequest.getBooks());
            author.setBooks(bookEntities);
        }

        authorRepo.save(author);
    }

    public AuthorResponse findAuthorById(Long id)
    {
       Author author =  authorRepo.findById(id).orElseThrow(NoSuchElementException::new);

        return AuthorResponse.builder().
                author_name(author.getAuthor_name()).
                books(author.getBooks()).build();
    }

    public void deleteAuthorById( Long id  )
    {
        if(authorRepo.findById(id).isPresent())
        {
            authorRepo.deleteById(id);
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public AuthorResponse updateAuthor(AuthorRequest authorRequest) {
        Author author = authorRepo.findById(authorRequest.getId())
                .orElseThrow(() -> new NoSuchElementException("Author not found"));

        if (authorRequest.getAuthor_name() != null) {
            author.setAuthor_name(authorRequest.getAuthor_name());
        }

        if (authorRequest.getBooks() != null && !authorRequest.getBooks().isEmpty()) {
            var bookEntities = booksRepo.findAllById(authorRequest.getBooks());
            author.setBooks(bookEntities);
        }

        Author saved = authorRepo.save(author);

        return modelMapper.map(saved, AuthorResponse.class);
    }

    public List<AuthorResponse> getAllAuthors()
    {
        return authorRepo.findAll().stream().map(this::toDto).toList();
    }

    public AuthorResponse toDto( Author author )
    {
        return modelMapper.map(author, AuthorResponse.class);
    }

}
