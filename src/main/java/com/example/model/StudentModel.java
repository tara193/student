package com.example.model;

import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

public class StudentModel {

        private UUID studentId;

        @NonNull
        private String Name;

        private String email;

    }
