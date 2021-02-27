package com.example.respository;

import com.example.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Courses, UUID> {
}
