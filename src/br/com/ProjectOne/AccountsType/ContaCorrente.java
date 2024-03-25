package br.com.ProjectOne.AccountsType;

public class ContaCorrente extends Conta{


    private static double tax = 0.1; // 10% of tax
    public ContaCorrente(Client client, int bankNumber) {
        super(client, bankNumber);
        setAccountType("CORRENTE");
    }
    @Override
    public void withdrawal(double value) {
        double applyTaxes = value + (value * tax);
        actualBalance -= applyTaxes;
        if(actualBalance < 0 ){
            System.out.println("Your balance have a debit of $"+actualBalance);
        }
    }
}
