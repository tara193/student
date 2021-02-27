package com.example.controller;

import com.example.entity.Student;
import com.example.respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getStudents (){
        return studentRepository.findAll();
    }
    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student){
        return studentRepository.save(student);
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudentCourse(@PathVariable UUID studentId, @Valid @RequestBody Student student) {
        return  studentRepository.findById(studentId)
                .map(student1 -> studentRepository.save(student1))
                .orElseThrow(()-> new RuntimeException("Student not found"));
    }

    @GetMapping("/students/courses/{courseId}")
    public List<Student> getStudentByCourse(@PathVariable UUID courseId) {
        return studentRepository.findByCoursesCourseName(courseId);
    }

}
