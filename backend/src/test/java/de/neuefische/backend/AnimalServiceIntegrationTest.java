package de.neuefische.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "CLOUD_NAME=duexg0xg3",
        "API_KEY=525157425224357",
        "API_SECRET=xGhRVQGh53lJeHwkumiYz2mZsjw"
})
class AnimalServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    AnimalService animalService;

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
        MockMultipartFile mockFile = new MockMultipartFile(
                "file", "image.jpg", "text/plain",
                "File content".getBytes(StandardCharsets.UTF_8));

        ResultActions resultActions = mockMvc.perform(multipart("/api/animals")
                .file(mockFile)
                .param("name", "dog")
                .param("favoriteFood", "bone")
                .param("type", "DOG")
                .param("dateOfBirth", "23/04/1996")
        );
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.name").value("dog"));
    }

    @Test
    @DirtiesContext
    void test_deleteAnimal() throws Exception {
        Animal newAnimal = new Animal("1", "cat", "fish", Type.CAT, "14/02/2002", "imageUrl", "1");
        animalRepository.save(newAnimal);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/animals/1"))
                .andExpect(status().isOk());

    }

    @Test
    @DirtiesContext
    void test_editAnimal() throws Exception {
        Animal newAnimal = new Animal("1", "cat", "fish", Type.CAT, "14/02/2002", "imageUrl", "1");
        animalRepository.save(newAnimal);
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/animals/1")
                                .content(objectMapper.writeValueAsBytes(new Animal("1", "dog", "bone", Type.DOG, "14/02/2000", "imageUrl", "1")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {"id": "1", "name": "dog", favoriteFood: "bone", "type": "DOG", "dateOfBirth": "14/02/2000", "imageUrl": "imageUrl"}
                        """));

    }
}
