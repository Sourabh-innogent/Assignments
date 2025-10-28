package com.librarymanagementsystem.lms.service;

import com.librarymanagementsystem.lms.dao.LibraryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    @Autowired
    LibraryDao libraryDao;

        public void borrowBook(Long memberId, Long bookId) {
          libraryDao.borrowBook(memberId,bookId);
            }

    public void returnBook(Long memberId, Long bookId) {
        libraryDao.returnBook(memberId,bookId);
    }
    }

