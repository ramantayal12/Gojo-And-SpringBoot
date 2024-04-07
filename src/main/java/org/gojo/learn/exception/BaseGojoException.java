package org.gojo.learn.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseGojoException extends Exception {

  public BaseGojoException(String message) {
    super(message);
  }

  public BaseGojoException(String message, Throwable cause) {
    super(message, cause);
  }

  public abstract String getErrorCode();

  public abstract HttpStatus getHttpStatus();

//  public abstract Status.Code getGrpcStatus();

}
