package de.neuefische.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class AnimalServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    void test_getAnimals() throws Exception {
        animalRepository.save(new Animal("1", "cat"));
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/animals"));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$[0].id").value("1"));
    }

    @Test
    @DirtiesContext
    void test_AddAnimal() throws Exception {
        String requestBody = """
                {"id":"1", "name":"dog"}
                """;
        ResultActions resultActions = mockMvc.perform(post("/api/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().json("""
                {"id":"1", "name":"dog"}
                """));
    }

    @Test
    @DirtiesContext
    void test_deleteAnimal() throws Exception {
        animalRepository.save(new Animal("1", "cat"));
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/animals/1"));
        resultActions.andExpect(status().isOk());
        resultActions.andDo(result -> System.out.println(result.getResponse().getStatus()));
    }
}
