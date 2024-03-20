public class AgeValidator {

    public static int isMoreEighteenOld(int age){
        if(!(age >= 18)){
            throw new FailedAccountCreateException("The client is minor of 18 years old");
        }
        return age;
    }
}
