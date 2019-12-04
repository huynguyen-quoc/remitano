package com.huynguyen.videosharing.domain.dto.request;

import com.huynguyen.videosharing.domain.Request;
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
public class VideoSharingDTO implements Request {

  private static final long serialVersionUID = 2005762300608840196L;

  private final String url;
  private final String title;
  private final String description;
}
