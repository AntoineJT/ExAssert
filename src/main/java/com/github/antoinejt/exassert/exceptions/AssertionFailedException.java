package com.github.antoinejt.exassert.exceptions;

public class AssertionFailedException extends RuntimeException {

  public AssertionFailedException() {
    super();
  }

  public AssertionFailedException(final String msg) {
    super(msg);
  }
}
