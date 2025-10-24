package com.librarymanagementsystem.lms.dao;


import com.librarymanagementsystem.lms.dto.AuthorRequest;
import com.librarymanagementsystem.lms.dto.AuthorResponse;
import com.librarymanagementsystem.lms.model.Author;
import com.librarymanagementsystem.lms.repo.AuthorRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class AuthorDao {

    @Autowired
    AuthorRepo authorRepo;

    @Autowired
    private ModelMapper modelMapper;

    public void addAuthor(AuthorRequest author)
    {
       authorRepo.save(modelMapper.map(author, Author.class));
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

    public AuthorResponse updateAuthor(AuthorRequest authorRequest)
    {
        Author author =  authorRepo.findById(authorRequest.getId()).orElseThrow(NoSuchElementException::new);
        if(author != null)
        {
            Author updated = modelMapper.map(authorRequest, Author.class);
            authorRepo.save(updated);
            return modelMapper.map(updated, AuthorResponse.class);
        }
        return null;
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
