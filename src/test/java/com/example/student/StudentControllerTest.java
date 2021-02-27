package com.example.student;

import com.example.controller.StudentController;
import com.example.entity.Courses;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class StudentControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    StudentController studentController;

    @Autowired
    ObjectMapper objectMapper;

//    @Autowired
//    EnrollRepository repository;


    @Test
    public void ShouldBeAbleToCreateTest() throws Exception {

        final Courses[] createdCourse = new Courses[1];

        this.mockMvc.perform(post("/courses")
                .contentType("application/json")
                .content("{\n" +
                        "\t\"courseName\": \"Math\",\n" +
                        "\t\"professorName\" : \"bean\",\n" +
                        "\t\"description\" : \"math class\"\n" +
                        "}")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Math")))
                .andDo(result -> {
                    String contentAsString = result.getResponse().getContentAsString();
                    createdCourse[0] = objectMapper.readValue(contentAsString, Courses.class);
                });

       UUID courseId =  createdCourse[0].getId();


//        studentController.createStudent(new Student("Tara","tara.rdb@gmail.com",
//                Set.of( new Courses("math","bob","math course"))));


        this.mockMvc.perform(post("/students")
                .contentType("application/json")
                .content("\t{\n" +
                        "\t\t\"name\" : \"Tara\",\n" +
                        "\t\t\"email\" : \"tara.rdb@gmail.co\",\n" +
                        "\t\t\"courses\" : {\n" +
                        "\t\t\t\"courseId\" : \"" + courseId + "\"" +
                        "\t\t}\n" +
                        "\t}")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Tara")));

        Thread.sleep(1000);
        this.mockMvc.perform(get("/students/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(Matchers.containsString("Tara")));

//        studentController.createStudent(new Student("Tara","tara.rdb@gmail.com",
//                Set.of( new Courses("math","bob","math course"))));

        this.mockMvc.perform(get("/students/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(Matchers.containsString("Tara")));

        this.mockMvc.perform(get("/courses/"))
                .andExpect(status().isOk())
                .andDo(print());

//        List<Enroll> all = repository.findAll();

//        log.info("enroll" + all.size());
    }
}


