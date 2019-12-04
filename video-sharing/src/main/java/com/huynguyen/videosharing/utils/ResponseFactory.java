package com.huynguyen.videosharing.utils;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseFactory {

  public static <T extends Serializable> ResponseEntity<T> accepted(@Nullable T data) {
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(data);
  }

  public static <T extends Serializable> ResponseEntity<T> created(@Nullable T data) {
    return ResponseEntity.status(HttpStatus.CREATED).body(data);
  }

  public static <T extends Serializable> ResponseEntity<T> success(@Nullable T data) {
    return ResponseEntity.status(HttpStatus.OK).body(data);
  }


}
