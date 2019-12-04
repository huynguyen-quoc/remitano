package com.huynguyen.videosharing.domain.dto.response;

import com.huynguyen.videosharing.domain.Response;
import com.huynguyen.videosharing.domain.dto.request.UserDTO;
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
public class VideoResponseDTO implements Response {

  private static final long serialVersionUID = 2005762300608840196L;
  private final Long id;
  private final String url;
  private final String title;
  private final String description;
  private final UserResponseDTO user;
}
