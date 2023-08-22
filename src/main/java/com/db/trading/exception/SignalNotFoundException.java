package com.db.trading.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SignalNotFoundException extends RuntimeException {
  public SignalNotFoundException(String exception) {
    super(exception);
  }
}