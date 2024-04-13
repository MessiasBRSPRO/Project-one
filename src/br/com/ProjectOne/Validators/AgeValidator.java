package br.com.ProjectOne.Validators;

import br.com.ProjectOne.Exceptions.AgeInvalidException;

public class AgeValidator {

    public static int isMoreEighteenOld(int age){
        boolean adultAge = age >= 18;
        try{
            if(!(adultAge)){
                throw new AgeInvalidException("To create account the client will be more 18 years old");
            }
        }catch (AgeInvalidException e){
            System.out.println("An exception has occured:"+e.getMessage());
        }
        return age;
    }
}
