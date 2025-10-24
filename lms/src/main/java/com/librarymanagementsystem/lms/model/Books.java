package com.librarymanagementsystem.lms.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String book_title;

    @NotNull
    Integer stock;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonIgnore
    Author author;

    @ManyToMany(mappedBy = "borrowedBooks")
    @JsonIgnore
    private List<Member> members;

}
