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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        []
                        """));
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
        resultActions.andExpect(jsonPath("$.name").value("dog"));
    }

    @Test
    @DirtiesContext
    void test_deleteAnimal() throws Exception {
        Animal newAnimal = new Animal("1", "cat");
        animalRepository.save(newAnimal);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/animals/1"))
                .andExpect(status().isOk());

    }

    @Test
    @DirtiesContext
    void test_editAnimal() throws Exception {
        Animal newAnimal = new Animal("1", "cat");
        animalRepository.save(newAnimal);
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/animals/1")
                                .content(objectMapper.writeValueAsBytes(new Animal("1", "dog")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {"id": "1", "name": "dog"}
                        """));

    }
}
