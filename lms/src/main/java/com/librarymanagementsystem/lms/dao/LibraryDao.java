package com.librarymanagementsystem.lms.dao;

import com.librarymanagementsystem.lms.model.Books;
import com.librarymanagementsystem.lms.model.Member;
import com.librarymanagementsystem.lms.repo.BooksRepo;
import com.librarymanagementsystem.lms.repo.MembersRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LibraryDao {

    @Autowired
    private  MembersRepo memberRepository;
    @Autowired
    private BooksRepo bookRepository;



    @Transactional
    public void borrowBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + memberId));

        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));

        if (book.getStock() < 1) {
            throw new RuntimeException("Book '" + book.getBook_title()+ "' is out of stock!");
        }

        book.setStock(book.getStock() - 1);
        member.getBorrowedBooks().add(book);

        bookRepository.save(book);
        memberRepository.save(member);
    }

    @Transactional
    public void returnBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Books book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!member.getBorrowedBooks().contains(book)) {
            throw new RuntimeException("This member has not borrowed this book!");
        }

        book.setStock(book.getStock() + 1);
        member.getBorrowedBooks().remove(book);

        memberRepository.save(member);
        bookRepository.save(book);
    }
}
