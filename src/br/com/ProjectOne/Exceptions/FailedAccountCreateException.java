package br.com.ProjectOne.Exceptions;

public class FailedAccountCreateException extends RuntimeException {
    public FailedAccountCreateException(String s) {
        super(s);
    }
}
