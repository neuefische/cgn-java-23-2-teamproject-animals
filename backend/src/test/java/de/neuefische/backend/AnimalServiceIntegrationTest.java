package de.neuefische.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AnimalServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext
    void get_list_of_animals() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/animals"));

        resultActions.andExpect(status().isOk()).andReturn();
        resultActions.andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DirtiesContext
    void confirms_a_animal_was_added() throws Exception {
        String requestBody = """
                {"id":"1", "name":"dog"}
                """;
        ResultActions resultActions = mockMvc.perform(post("/api/animal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$", hasSize(1)));
    }
}
