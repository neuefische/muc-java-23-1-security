package de.neuefische.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext
    @WithMockUser
    void getCats_whenLoggedIn_expectStatus200AndReturnString() throws Exception {
        mockMvc.perform(get("/api/cats"))
                .andExpect(status().isOk())
                .andExpect(content().string("Alle Katzen"));
    }

    @Test
    @DirtiesContext
    void getCats_whenNotLoggedIn_expectStatus401() throws Exception {
        mockMvc.perform(get("/api/cats"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DirtiesContext
    @WithMockUser(roles = "ADMIN")
    void getCatById_whenLoggedInAndAuthorized_expectStatus200AndReturnString() throws Exception {
        mockMvc.perform(post("/api/cats/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Katze mit ID 1!"));
    }

    @Test
    @DirtiesContext
    void getCatById_whenNotLoggedIn_expectStatus401() throws Exception {
        mockMvc.perform(post("/api/cats/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DirtiesContext
    @WithMockUser
    void getCatById_whenLoggedInAndNotAuthorized_expectStatus403() throws Exception {
        mockMvc.perform(post("/api/cats/1"))
                .andExpect(status().isForbidden());
    }
}