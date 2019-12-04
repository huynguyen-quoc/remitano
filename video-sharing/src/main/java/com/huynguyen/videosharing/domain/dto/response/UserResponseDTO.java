package com.huynguyen.videosharing.domain.dto.response;

import com.huynguyen.videosharing.domain.Response;
import com.huynguyen.videosharing.domain.enums.UserStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Getter
@Builder
public class UserResponseDTO implements Response {

  private static final long serialVersionUID = 4411714987575446966L;
  private final Long id;
  private final String username;
  private final String email;
  private final UserStatus status;
}
