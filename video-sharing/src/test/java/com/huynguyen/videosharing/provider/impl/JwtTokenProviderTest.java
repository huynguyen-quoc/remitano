package com.huynguyen.videosharing.provider.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.provider.TokenProvider;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {

  private TokenProvider jwtTokenProvider = new JwtTokenProvider();

  @Test
  void generateShouldReturnNonNullToken() {
    Authentication mockAuthentication = mock(Authentication.class);
    when(mockAuthentication.getPrincipal()).thenReturn(mock(User.class));

    String jwt = jwtTokenProvider.generate(mockAuthentication);
    assertThat(jwt).isNotNull();
  }

  @Test
  void verifyShouldReturnTrueForValidJWT() {
    Authentication mockAuthentication = mock(Authentication.class);
    when(mockAuthentication.getPrincipal()).thenReturn(mock(User.class));

    String jwt = jwtTokenProvider.generate(mockAuthentication);

    boolean result = jwtTokenProvider.verify(jwt);
    assertThat(result).isTrue();
  }

  @Test
  void verifyShouldReturnFalseForInValidJWT() {
    Authentication mockAuthentication = mock(Authentication.class);
    when(mockAuthentication.getPrincipal()).thenReturn(mock(User.class));

    String jwt = jwtTokenProvider.generate(mockAuthentication);

    boolean result = jwtTokenProvider.verify(jwt + "invalid");
    assertThat(result).isFalse();
  }

  @Test
  void parseShouldReturnNotEmptyClaims() {
    Authentication mockAuthentication = mock(Authentication.class);
    User mockUser = mock(User.class);
    when(mockAuthentication.getPrincipal()).thenReturn(mockUser);
    String jwt = jwtTokenProvider.generate(mockAuthentication);

    Map<String, Object> result = jwtTokenProvider.parse(jwt);
    assertThat(result).isNotEmpty();
    assertThat(result.get("id")).isEqualTo(mockUser.getId().intValue());
    assertThat(result.get("username")).isEqualTo(mockUser.getUsername());
  }
}