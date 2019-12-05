package com.huynguyen.videosharing.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.huynguyen.videosharing.provider.TokenProvider;
import com.huynguyen.videosharing.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {UserController.class})
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @MockBean
  private UsersMapper usersMapper;

  @MockBean
  private TokenProvider tokenProvider;

  @MockBean
  private AuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @MockBean
  private UserDetailsService userDetailsService;

  @MockBean
  private PasswordEncoder passwordEncoder;

  @Test
  public void createShouldReturnCorrectUserAndCorrectHttpStatusAndCallServiceATimes()
      throws Exception {

    UserDTO request = UserDTO.builder().password("test")
        .email("test_email").build();
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString = objectMapper.writeValueAsString(request);
    User model = mock(User.class);
    UserResponseDTO response = UserResponseDTO.builder().id(model.getId())
        .status(UserStatus.CREATED).email(model.getUsername()).build();
    when(usersMapper.transform(model)).thenReturn(response);
    when(usersMapper.transform(request)).thenReturn(model);
    when(userService.create(any(User.class))).thenReturn(model);
    when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encrypted_text");

    mockMvc.perform(post("/users")
        .content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", is(response.getId().intValue())))
        .andExpect(jsonPath("$.status", is(response.getStatus().name())))
        .andExpect(jsonPath("$.email", is(response.getEmail())));

    verify(userService, times(1)).create((any(User.class)));
  }
}