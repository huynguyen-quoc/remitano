package com.huynguyen.videosharing.domain.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.huynguyen.videosharing.domain.Response;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Getter
@Builder
@RequiredArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ErrorResponse implements Response {

  private static final long serialVersionUID = 7816379164947472325L;

  private final String error;
  private final String errorDescription;

}
