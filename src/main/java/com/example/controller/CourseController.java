package com.example.controller;

import com.example.entity.Courses;
import com.example.respository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/hello")
    public String sayhello(){
        return "Hello i m running";
    }

    @PostMapping("/courses")
    public Courses createCourse(@Valid @RequestBody Courses courses){
        return courseRepository.save(courses);
    }

    @DeleteMapping("/courses/{courseId}")
    public String deleteCourse(@PathVariable UUID courseId){
        return courseRepository.findById(courseId)
                .map(courses ->{
                    courseRepository.delete(courses);
                return "Deleted!!";
                }).orElseThrow(()-> new RuntimeException("Course not found"));
    }

    @GetMapping("/courses")
    public List<Courses> getStudents (){
        return courseRepository.findAll();
    }

//    @GetMapping("/courses/{courseId}")
//    public List<Student> getStudentByCourse(@PathVariable UUID courseId){
//        return studentRepository.findByCourseId(courseId);
////        return null;
//    }
}
