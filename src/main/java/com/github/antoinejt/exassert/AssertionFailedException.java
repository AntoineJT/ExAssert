package com.github.antoinejt.exassert;

@SuppressWarnings("unused")
public class AssertionFailedException extends Exception {
    public AssertionFailedException() {
        super();
    }

    public AssertionFailedException(String msg) {
        super(msg);
    }
}
