package br.com.ProjectOne.Exceptions;

public class NegativeBalanceException extends RuntimeException{
    public NegativeBalanceException(String msg){
        super(msg);
    }
}
