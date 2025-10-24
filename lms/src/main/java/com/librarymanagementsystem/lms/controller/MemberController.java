package com.librarymanagementsystem.lms.controller;


import com.librarymanagementsystem.lms.dto.AuthorRequest;
import com.librarymanagementsystem.lms.dto.AuthorResponse;
import com.librarymanagementsystem.lms.dto.MemberRequest;
import com.librarymanagementsystem.lms.dto.MemberResponse;
import com.librarymanagementsystem.lms.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService service;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody MemberRequest memberRequest) {
        service.add(memberRequest);
        return  ResponseEntity.ok("Member Added!!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        return ResponseEntity.ok(service.getAllMembers());
    }

    @PatchMapping("/update")
    public ResponseEntity<MemberResponse> update( @RequestBody MemberRequest memberRequest ){
        return ResponseEntity.ok(service.updateMember(memberRequest));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteMemberById(id);
    }

}
