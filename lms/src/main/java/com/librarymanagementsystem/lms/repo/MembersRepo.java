package com.librarymanagementsystem.lms.repo;

import com.librarymanagementsystem.lms.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepo extends JpaRepository<Member,Long> {
}
