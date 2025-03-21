package com.hacksisters.reborn_market_backend.register;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacksisters.reborn_market_backend.product.ProductRepository;
import com.hacksisters.reborn_market_backend.user.User;
import com.hacksisters.reborn_market_backend.user.UserDtoRequest;
import com.hacksisters.reborn_market_backend.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserRegisterIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User("test@example.com", "testuser", "encoded_password");
        userRepository.save(user);
    }

    @Test
    void testRegisterNewUser() throws Exception {

        String encodedPassword = Base64.getEncoder().encodeToString("password123".getBytes());
        UserDtoRequest request = new UserDtoRequest("new@example.com", "newuser", encodedPassword);

        mockMvc.perform(post("/api/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Success")));

        List<User> users = userRepository.findAll();
        assertThat(users, hasSize(2));
    }

    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/api/v1/private/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].email", is("test@example.com")))
                .andExpect(jsonPath("$[0].username", is("testuser")));
    }

    @Test
    void testDeleteUser() throws Exception {

        Long userId = userRepository.findAll().get(0).getId();

        mockMvc.perform(delete("/api/v1/user/" + userId))
                .andExpect(status().isNoContent());

        assertThat(userRepository.existsById(userId), is(false));
    }
}
