package com.huynguyen.videosharing.exceptions.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UnauthorizedMessage implements ExceptionMessage {
  UNAUTHORIZED("U10001", "Unauthorized");

  private String message;
  private String description;

  @Override
  public int getStatusCode() {
    return 401;
  }
}
