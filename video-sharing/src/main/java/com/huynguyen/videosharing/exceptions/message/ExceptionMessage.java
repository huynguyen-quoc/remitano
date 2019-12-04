package com.huynguyen.videosharing.exceptions.message;

public interface ExceptionMessage {

  String getMessage();

  String getDescription();

  default int getStatusCode() {
    return 200;
  }
}
