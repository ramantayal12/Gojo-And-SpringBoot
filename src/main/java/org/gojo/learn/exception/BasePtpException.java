package org.gojo.learn.exception;

import org.springframework.http.HttpStatus;

public abstract class BasePtpException extends Exception {

  public BasePtpException(String message) {
    super(message);
  }

  public BasePtpException(String message, Throwable cause) {
    super(message, cause);
  }

  public abstract String getErrorCode();

  public abstract HttpStatus getHttpStatus();

//  public abstract Status.Code getGrpcStatus();

}
