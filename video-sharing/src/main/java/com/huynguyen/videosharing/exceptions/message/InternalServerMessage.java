package com.huynguyen.videosharing.exceptions.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InternalServerMessage implements ExceptionMessage {
  INTERNAL_SERVER_ERROR("I10001", "Internal server error");

  private String message;
  private String description;


  @Override
  public int getStatusCode() {
    return 500;
  }
}
