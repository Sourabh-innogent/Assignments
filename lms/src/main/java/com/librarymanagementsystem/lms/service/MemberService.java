package com.librarymanagementsystem.lms.service;

import com.librarymanagementsystem.lms.dao.MemberDao;
import com.librarymanagementsystem.lms.dto.MemberRequest;
import com.librarymanagementsystem.lms.dto.MemberResponse;
import com.librarymanagementsystem.lms.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MemberService {

    @Autowired
    MemberDao memberDao;

    public void add(MemberRequest member){
        memberDao.add(member);
    }

    public MemberResponse findMemberById(Long id)
    {
        return memberDao.findMemberById(id);
    }

    public void deleteMemberById( Long id  )
    {
        memberDao.deleteMemberById(id);
    }

    public MemberResponse updateMember(MemberRequest memberRequest)
    {
        return memberDao.updateMember(memberRequest);
    }

    public List<MemberResponse> getAllMembers()
    {
        return memberDao.getAllMembers();
    }
}
