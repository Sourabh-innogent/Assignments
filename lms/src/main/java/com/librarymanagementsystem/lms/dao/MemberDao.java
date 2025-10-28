package com.librarymanagementsystem.lms.dao;

import com.librarymanagementsystem.lms.dto.MemberRequest;
import com.librarymanagementsystem.lms.dto.MemberResponse;
import com.librarymanagementsystem.lms.model.Books;
import com.librarymanagementsystem.lms.model.Member;
import com.librarymanagementsystem.lms.repo.BooksRepo;
import com.librarymanagementsystem.lms.repo.MembersRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class MemberDao {
    
    @Autowired
    MembersRepo membersRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BooksRepo booksRepo;

    public void add(MemberRequest memberRequest) {
        List<Books> borrowedBooks = booksRepo.findAllById(memberRequest.getBookIds());

        for (Books b : borrowedBooks) {
            if (b.getStock() < 1) {
                throw new RuntimeException("Book " + b.getBook_title() + " is out of stock!");
            }
            b.setStock(b.getStock() - 1);
        }

        booksRepo.saveAll(borrowedBooks);

        Member member = Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .borrowedBooks(borrowedBooks)
                .build();

        membersRepo.save(member);
    }

    public MemberResponse findMemberById(Long id)
    {
        Member member =  membersRepo.findById(id).orElseThrow(NoSuchElementException::new);
        return modelMapper.map(member, MemberResponse.class);
    }

    public void deleteMemberById( Long id  )
    {
        if(membersRepo.findById(id).isPresent())
        {
            membersRepo.deleteById(id);
        }
        else {
            throw new NoSuchElementException();
        }
    }

    @Transactional
    public MemberResponse updateMember(MemberRequest memberRequest) {
        Member member = membersRepo.findById(memberRequest.getMember_id())
                .orElseThrow(() -> new NoSuchElementException("Member not found"));

        member.setName(memberRequest.getName());
        member.setEmail(memberRequest.getEmail());

        List<Books> oldBooks = member.getBorrowedBooks();
        List<Books> newBooks = booksRepo.findAllById(memberRequest.getBookIds());

        for (Books b : oldBooks) {
            if (!newBooks.contains(b)) {
                b.setStock(b.getStock() + 1);
            }
        }

        for (Books b : newBooks) {
            if (!oldBooks.contains(b)) {
                if (b.getStock() < 1) {
                    throw new RuntimeException("Book '" + b.getBook_title() + "' is out of stock!");
                }
                b.setStock(b.getStock() - 1);
            }
        }

        booksRepo.saveAll(newBooks);
        booksRepo.saveAll(oldBooks);

        member.setBorrowedBooks(newBooks);
        membersRepo.save(member);

        return modelMapper.map(member, MemberResponse.class);
    }


    public List<MemberResponse> getAllMembers()
    {
        return membersRepo.findAll().stream().map(this::toDto).toList();
    }

    public MemberResponse toDto( Member member )
    {
        return modelMapper.map(member, MemberResponse.class);
    }
}
