package com.huynguyen.videosharing.exceptions.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BadRequestMessage implements ExceptionMessage {
  INVALID_REQUEST_ERROR("B10001", "Bad Request");

  private String message;
  private String description;

  @Override
  public int getStatusCode() {
    return 400;
  }

}
