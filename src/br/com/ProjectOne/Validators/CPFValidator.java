package br.com.ProjectOne.Validators;

import br.com.ProjectOne.Exceptions.InvalidDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPFValidator {

    private static String regexCPF ="\\b(?:\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})\\b";


    public static String validatorCPF(String cpf){
        Pattern regex = Pattern.compile(regexCPF); //the regex
        Matcher matcher = regex.matcher(cpf); // Where the regex will be applyed;
        boolean isValid = matcher.find();
        try{
            if(!(isValid)){
                throw new InvalidDataException("Your cpf is invalid, please insert a valid data!");
            }
        }catch (InvalidDataException e){
            System.out.println("An Exception has occurred:"+e.getMessage());
        }
        return cpf;
    }
}
