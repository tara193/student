package com.example.respository;

import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

//    @Query("SELECT m from Student m , Courses c where m.courses.courseName = c.courseName ")
    List<Student> findByCoursesCourseName(UUID courseId);
}
