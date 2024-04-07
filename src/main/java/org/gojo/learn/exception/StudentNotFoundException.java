package org.gojo.learn.exception;

import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends BaseGojoException {

  public StudentNotFoundException() {
    super("STUDENT_NOT_FOUND");
  }

  @Override
  public String getErrorCode() {
    return "ERROR_001";
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.NOT_FOUND;
  }
}
