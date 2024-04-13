package br.com.ProjectOne.AccountsType;

import br.com.ProjectOne.DataBasePackage.CRUDConta;
import br.com.ProjectOne.Exceptions.NegativeBalanceException;

public class ContaCorrente extends Conta{
    //this class is a son of Conta

    private static double tax = 0.1; // 10% of tax
    public ContaCorrente(Client client, int bankNumber) {
        super(client, bankNumber);
        setAccountType("CORRENTE");
    }
    @Override
    public void withdrawal(double value) {
        CRUDConta crudConta = new CRUDConta();
        double applyTaxes = value + (value * tax);
        actualBalance -= applyTaxes;
        boolean balanceValidation = actualBalance < 0;
        try{
            if(balanceValidation){
                throw new NegativeBalanceException("Your balance have a debit of $"+actualBalance);
            }
            operationsExtract.add("WithDrawal $"+value + "+ tax:$"+(value * tax));
            crudConta.update(this);
        }catch (NegativeBalanceException e){
            System.out.println("An exception has occurred:"+e.getMessage());
        }

    }
}
