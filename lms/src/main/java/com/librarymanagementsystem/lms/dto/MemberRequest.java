package com.librarymanagementsystem.lms.dto;
import com.librarymanagementsystem.lms.model.Books;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
public class MemberRequest{

    Long member_id;

    private String name;

    private String email;

    private List<Long> bookIds;
}
