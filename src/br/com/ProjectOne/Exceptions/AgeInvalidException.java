package br.com.ProjectOne.Exceptions;

public class AgeInvalidException extends RuntimeException{
    public AgeInvalidException(String msg){
        super(msg);
    }
}
