package com.huynguyen.videosharing.controllers;

import com.huynguyen.videosharing.domain.dto.request.TokenDTO;
import com.huynguyen.videosharing.domain.dto.response.TokenResponseDTO;
import com.huynguyen.videosharing.provider.TokenProvider;
import com.huynguyen.videosharing.services.UserService;
import com.huynguyen.videosharing.utils.ResponseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

  private final TokenProvider tokenProvider;
  private final AuthenticationManager authenticationManager;

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
      MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TokenResponseDTO> generateToken(
      @RequestBody TokenDTO request) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = tokenProvider.generate(authentication);
    return ResponseFactory.created(TokenResponseDTO.builder().type("bearer").token(jwt).build());
  }


}
