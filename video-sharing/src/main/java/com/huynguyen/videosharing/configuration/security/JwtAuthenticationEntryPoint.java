package com.huynguyen.videosharing.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huynguyen.videosharing.domain.dto.response.ErrorResponse;
import com.huynguyen.videosharing.exceptions.message.UnauthorizedMessage;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper mapper;

  @Override
  public void commence(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, AuthenticationException e)
      throws IOException {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .error(UnauthorizedMessage.UNAUTHORIZED.getMessage())
        .errorDescription(UnauthorizedMessage.UNAUTHORIZED.getDescription()).build();
    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    httpServletResponse.getWriter().print(mapper.writeValueAsString(errorResponse));
  }
}
