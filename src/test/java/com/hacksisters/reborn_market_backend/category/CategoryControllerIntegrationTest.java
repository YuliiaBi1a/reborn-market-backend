package com.hacksisters.reborn_market_backend.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category testCategory;

    @BeforeEach
    void setup() {
        testCategory = new Category();
        testCategory.setName("Test Category");
        categoryRepository.save(testCategory);
    }

    @Test
    void testCreateCategory() throws Exception {
        CategoryDtoRequest request = new CategoryDtoRequest("New Category");

        mockMvc.perform(post("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", is(request.name())));
    }

    @Test
    void testGetAllCategories() throws Exception {
        mockMvc.perform(get("/api/v1/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(11)))); // Очікуємо мінімум 11 записів
    }

    @Test
    void testGetCategoryById() throws Exception {
        mockMvc.perform(get("/api/v1/category/" + testCategory.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testCategory.getId().intValue())))
                .andExpect(jsonPath("$.name", is(testCategory.getName())));
    }

    @Test
    void testUpdateCategory() throws Exception {
        CategoryDtoRequest updateRequest = new CategoryDtoRequest("Updated Category");

        mockMvc.perform(put("/api/v1/category/" + testCategory.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testCategory.getId().intValue())))
                .andExpect(jsonPath("$.name", is(updateRequest.name())));
    }

    @Test
    void testDeleteCategory() throws Exception {
        mockMvc.perform(delete("/api/v1/category/" + testCategory.getId()))
                .andExpect(status().isNoContent());
    }
}
