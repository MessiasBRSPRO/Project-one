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
        CRUDConta crudConta = new CRUDConta();
        double applyTaxes = value + (value * tax);
        this.actualBalance -= applyTaxes;
        boolean balanceValidation = actualBalance < 0;
        try{
            if(balanceValidation){
                //If the withdrawal value is more bigger of your balance, you will be enter in debit
                throw new NegativeBalanceException("your account is in debit, have a negative balance");
            }
            operationsExtract.add("WithDrawal $"+value + "+ tax:$"+(value * tax));
            crudConta.update(this);
        }catch (NegativeBalanceException e){
            System.out.println("an Exception has occurred:"+e.getMessage());
        }
    }


}
