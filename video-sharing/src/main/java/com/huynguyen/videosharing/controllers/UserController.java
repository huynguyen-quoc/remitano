package com.huynguyen.videosharing.controllers;

import com.huynguyen.videosharing.domain.dto.request.UserDTO;
import com.huynguyen.videosharing.domain.dto.response.UserResponseDTO;
import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.mapper.users.UsersMapper;
import com.huynguyen.videosharing.services.UserService;
import com.huynguyen.videosharing.utils.ResponseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UsersMapper mapper;
  private final UserService service;
  private final PasswordEncoder passwordEncoder;

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
      MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<UserResponseDTO> create(
      @RequestBody UserDTO request) {
    User model = mapper.transform(request);
    model.setPassword(passwordEncoder.encode(model.getPassword()));
    log.info("create users with info {}", model);
    UserResponseDTO response = mapper
        .transform(service.create(model));
    return ResponseFactory.created(response);
  }


}
