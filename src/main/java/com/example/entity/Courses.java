package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()
@JsonIgnoreProperties(value= "courses")
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NonNull
    private String courseName;

    @NonNull
    private String professorName;

    private String description;


//    @JsonBackReference
    @JsonIgnore
//    @JsonIgnoreProperties(value= "courses")
    @ManyToMany(mappedBy = "courses",fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

}
