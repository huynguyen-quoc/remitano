package com.huynguyen.videosharing.controllers;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huynguyen.videosharing.domain.dto.request.TokenDTO;
import com.huynguyen.videosharing.domain.dto.request.UserDTO;
import com.huynguyen.videosharing.domain.dto.response.TokenResponseDTO;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {TokenController.class})
@AutoConfigureMockMvc(addFilters = false)
class TokenControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @MockBean
  private AuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @MockBean
  private UserDetailsService userDetailsService;

  @MockBean
  private TokenProvider tokenProvider;

  @MockBean
  private AuthenticationManager authenticationManager;


  @Test
  void generateTokenShouldReturnCorrectTokenAndCorrectHttpStatusAndCallServiceATimes()
      throws Exception {
    TokenDTO request = TokenDTO.builder().password("test")
        .username("test_username").build();
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonString = objectMapper.writeValueAsString(request);
    Authentication mockAuthentication = mock(Authentication.class);

    when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(mockAuthentication);
    when(tokenProvider.generate(any(Authentication.class))).thenReturn("token_text");

    mockMvc.perform(post("/token")
        .content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.type", is("bearer")))
        .andExpect(jsonPath("$.token", is("token_text")));
  }
}