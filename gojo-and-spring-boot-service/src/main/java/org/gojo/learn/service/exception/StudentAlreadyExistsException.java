package org.gojo.learn.service.exception;

import org.springframework.http.HttpStatus;

public class StudentAlreadyExistsException extends BaseGojoException {

  public StudentAlreadyExistsException() {
    super("STUDENT_ALREADY_EXISTS");
  }

  @Override
  public String getErrorCode() {
    return "ERROR_002";
  }

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.NOT_FOUND;
  }
}
