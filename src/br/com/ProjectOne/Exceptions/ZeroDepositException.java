package br.com.ProjectOne.Exceptions;

public class ZeroDepositException extends RuntimeException{
    public ZeroDepositException(String msg){
        super(msg);
    }
}
