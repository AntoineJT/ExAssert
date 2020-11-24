package com.github.antoinejt.exassert.exceptions;

@SuppressWarnings("unused")
public class AssertionFailedException extends Exception {
    public AssertionFailedException() {
        super();
    }

    public AssertionFailedException(String msg) {
        super(msg);
    }
}
