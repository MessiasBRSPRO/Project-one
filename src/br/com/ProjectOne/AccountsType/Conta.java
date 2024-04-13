package br.com.ProjectOne.AccountsType;

import br.com.ProjectOne.DataBasePackage.CRUDConta;
import br.com.ProjectOne.Exceptions.ZeroDepositException;
import br.com.ProjectOne.Validators.AgeValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta {
    //This is a abstract class than will be used for other son-class

    private Client client;
    private int bankNumber;
    protected double actualBalance;
    private String accountType;
    protected List<String> operationsExtract;

    public Conta(Client client, int bankNumber){
        this.client = client;
        AgeValidator.isMoreEighteenOld(client.getAge());//Only > of eighteen years old can open a account
        this.bankNumber = bankNumber;
        this.actualBalance = 0;//All created accounts stars with actual balance $0
        this.operationsExtract = new ArrayList<>(); //Collection with contains all operations of each client

    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Client getClient() {
        return client;
    }

    public int getBankNumber() {
        return bankNumber;
    }

    public double getActualBalance() {
        return actualBalance;
    }

    public List<String> getOperationsExtract() {
        return Collections.unmodifiableList(operationsExtract);
        // empowering the restriction of information
    }

    public double deposit(double value) {
        //This is a method than will add a value in balance (this is global method)
        double newValue  = value > 0 ? actualBalance+=value : 0;
        try{
            if(value <= 0){
                throw new ZeroDepositException("Deposit will be realized only if is bigger than 0");
            }
        }catch (ZeroDepositException e){
            System.out.println("An exception has occurred:"+e.getMessage());
        }
        CRUDConta crudConta = new CRUDConta();
        crudConta.update(this);
        operationsExtract.add("Deposit $"+ value);//Adding the opration in Data's structure
        return newValue;
    }

    public abstract void withdrawal(double value);
    //The method withdrawal is abstract because your structure will be modify cus different taxes will be applied

    public void transferCash(Conta destinatario, double value) {
        //Method global will be realize transference of account to other;
        withdrawal(value);
        destinatario.deposit(value);
        CRUDConta crudConta = new CRUDConta();
        crudConta.update(this);//Method will be update the actualBalance in Database(contasBancarias)
        operationsExtract.add("Transference to " + destinatario.getClient().getName() + " Value $"+value);
    }

    @Override
    public String toString() {
        return "Client:"+getClient().getName() + ", BankNumber:"+getBankNumber() + ", Actual balance:$"+getActualBalance();
    }
}
