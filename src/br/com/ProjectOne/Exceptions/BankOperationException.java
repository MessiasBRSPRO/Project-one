package br.com.ProjectOne.Exceptions;

public class BankOperationException extends RuntimeException {
    public BankOperationException(String s) {
        super(s);
    }
}
