package br.com.ProjectOne.AccountsType;

import br.com.ProjectOne.DataBasePackage.CRUDConta;

public class ContaBancaria extends Conta{
    //This class is son of Conta

    private static double tax = 0.05;//Tax of 5% for withdrawal in this type Account
    public ContaBancaria(Client client, int bankNumber) {
        super(client, bankNumber);
        setAccountType("BANCARIA");
    }

    @Override // this decorator signalize the modification of existent method in mother-Class
    public void withdrawal(double value) {
        CRUDConta crudConta = new CRUDConta();
        double applyTaxes = value + (value * tax);
        this.actualBalance -= applyTaxes;
        if(actualBalance < 0){
            //If the withdrawal value is more bigger of your balance, you will be enter in debit
            System.out.println("You have a debit of $"+actualBalance);
        }
        operationsExtract.add("WithDrawal $"+value + "+ tax:$"+(value * tax));
        crudConta.update(this);
    }


}
