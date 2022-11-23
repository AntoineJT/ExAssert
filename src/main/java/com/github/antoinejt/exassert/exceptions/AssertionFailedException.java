package com.github.antoinejt.exassert.exceptions;

@SuppressWarnings("serial")
public class AssertionFailedException extends Exception {
  public AssertionFailedException() {
    super();
  }

  public AssertionFailedException(String msg) {
    super(msg);
  }
}
