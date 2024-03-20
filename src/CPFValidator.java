import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPFValidator {

    private static String regexCPF ="\\b(?:\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})\\b";

    public static String validatorCPF(String cpf){
        Pattern regex = Pattern.compile(regexCPF); //the regex
        Matcher matcher = regex.matcher(cpf); // Where the regex will be applyed;
        if(!(matcher.find())){
            throw new InvalidDataException("The cpf is invalid! verify and try again.");
        }
        return cpf;
    }
}
