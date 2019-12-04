package com.huynguyen.videosharing.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huynguyen.videosharing.domain.dto.request.UserDTO;
import com.huynguyen.videosharing.domain.dto.response.UserResponseDTO;
import com.huynguyen.videosharing.domain.enums.UserStatus;
import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.mapper.users.UsersMapper;
import com.huynguyen.videosharing.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({UserController.class})
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @MockBean
  private UsersMapper usersMapper;

  @Test
  public void createShouldReturnCorrectUserAndCorrectHttpStatusAndCallServiceATimes()
      throws Exception {

    UserDTO request = UserDTO.builder().email("test@gmail.com").password("test")
        .username("test_username").build();
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString = objectMapper.writeValueAsString(request);
    User model = mock(User.class);
    UserResponseDTO response = UserResponseDTO.builder().email(model.getEmail()).id(model.getId())
        .status(UserStatus.CREATED).username(model.getUsername()).build();
    when(usersMapper.transform(model)).thenReturn(response);
    when(usersMapper.transform(request)).thenReturn(model);
    when(userService.create(any(User.class))).thenReturn(model);

    mockMvc.perform(post("/users")
        .content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(response.getId().intValue())))
        .andExpect(jsonPath("$.email", is(response.getEmail())))
        .andExpect(jsonPath("$.status", is(response.getStatus().name())))
        .andExpect(jsonPath("$.username", is(response.getUsername())));
  }
}