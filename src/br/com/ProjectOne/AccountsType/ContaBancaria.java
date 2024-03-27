package br.com.ProjectOne.AccountsType;

import br.com.ProjectOne.DataBasePackage.CRUDConta;

public class ContaBancaria extends Conta{

    private static double tax = 0.05;
    public ContaBancaria(Client client, int bankNumber) {
        super(client, bankNumber);
        setAccountType("BANCARIA");
    }

    @Override
    public void withdrawal(double value) {
        CRUDConta crudConta = new CRUDConta();
        double applyTaxes = value + (value * tax);
        this.actualBalance -= applyTaxes;
        if(actualBalance < 0){
            System.out.println("You have a debit of $"+actualBalance);
        }
        crudConta.update(this);
    }


}
