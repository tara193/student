package com.example.student;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest {


	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() throws Exception {
		this.mockMvc.perform(get("/hello")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("Hello i m running"));
	}

	@Test
	public void shouldBeAbleToCreateNewCourse() throws Exception {
		this.mockMvc.perform(post("/courses")
				.contentType("application/json")
				.content("{\n" +
						"\t\"courseName\": \"Math\",\n" +
						"\t\"professorName\" : \"bean\",\n" +
						"\t\"description\" : \"math class\"\n" +
						"}")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(Matchers.containsString("Math")));
	}

	@Test
	public void shouldBeAbleToCreateNewStudent() throws Exception {
		this.mockMvc.perform(post("/courses")
				.contentType("application/json")
				.content("{\n" +
						"\t\"courseName\": \"Math\",\n" +
						"\t\"professorName\" : \"bean\",\n" +
						"\t\"description\" : \"math class\"\n" +
						"}")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(Matchers.containsString("Math")));

		this.mockMvc.perform(post("/students")
				.contentType("application/json")
				.content("\t{\n" +
						"\t\t\"name\" : \"Tara\",\n" +
						"\t\t\"email\" : \"tara.rdb@gmail.co\",\n" +
						"\t\t\"course\" : {\n" +
						"\t\t\t\"courseName\" : \"Math\"\n" +
						"\t\t}\n" +
						"\t}")).andDo(print())
				.andExpect(status().isOk());



		this.mockMvc.perform(get("/students"))
				.andExpect(status().isOk())
				.andDo(print());

	}

}



