package com.huynguyen.videosharing.exceptions;

import com.huynguyen.videosharing.exceptions.message.ExceptionMessage;
import com.huynguyen.videosharing.exceptions.message.InternalServerMessage;
import javax.persistence.Transient;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

  private static final long serialVersionUID = 8133784150850998787L;

  @Transient
  private final transient ExceptionMessage error;

  public ApplicationException() {
    this.error = InternalServerMessage.INTERNAL_SERVER_ERROR;
  }

  public ApplicationException(String message) {
    super(message);
    this.error = InternalServerMessage.INTERNAL_SERVER_ERROR;
  }

  public ApplicationException(
      ExceptionMessage error) {
    super(error.getDescription());
    this.error = error;
  }

  public ApplicationException(Throwable cause, ExceptionMessage error) {
    super(error.getDescription(), cause);
    this.error = error;
  }

  public ApplicationException(String message, ExceptionMessage error) {
    super(message);
    this.error = error;
  }


}
