package com.example.model;

import lombok.NonNull;
import java.util.UUID;

public class CourseModel {

    private UUID courseId;

    @NonNull
    private String courseName;

    @NonNull
    private String professorName;

    private String description;
}
