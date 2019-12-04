package com.huynguyen.videosharing.exceptions.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ForbiddenMessage implements ExceptionMessage {
  FORBIDDEN("F10001", "Forbidden");

  private String message;
  private String description;

  @Override
  public int getStatusCode() {
    return 403;
  }
}
