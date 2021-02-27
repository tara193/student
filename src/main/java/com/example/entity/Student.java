package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Student  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NonNull
    private String Name;


    private String email;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="enroll",
            joinColumns=@JoinColumn(name="student_id", nullable = false, updatable = false),
            inverseJoinColumns= @JoinColumn(name="course_id", nullable = false, updatable = false)
    )
    private Set<Courses> courses = new HashSet<>();


}
