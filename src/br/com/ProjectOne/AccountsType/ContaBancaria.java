package br.com.ProjectOne.AccountsType;

import br.com.ProjectOne.DataBasePackage.CRUDConta;
import br.com.ProjectOne.Exceptions.NegativeBalanceException;

public class ContaBancaria extends Conta{
    //This class is son of Conta

    private static double tax = 0.05;//Tax of 5% for withdrawal in this type Account
    public ContaBancaria(Client client, int bankNumber) {
        super(client, bankNumber);
        setAccountType("BANCARIA");
    }

    @Override // this decorator signalize the modification of existent method in mother-Class
    public void withdrawal(double value) {
        double valueTaxed = value + (value * tax);
        super.withdrawal(valueTaxed);
    }


}
