package br.com.ProjectOne.AccountsType;

import br.com.ProjectOne.DataBasePackage.CRUDConta;
import br.com.ProjectOne.Validators.AgeValidator;

public abstract class Conta {
    //This is a abstract class than will be used for other son-class

    private Client client;
    private int bankNumber;
    protected double actualBalance;
    private String accountType;

    public Conta(Client client, int bankNumber){
        this.client = client;
        AgeValidator.isMoreEighteenOld(client.getAge());
        this.bankNumber = bankNumber;
        this.actualBalance = 0;

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

    public double deposit(double value) {
        double newValue  = value > 0 ? actualBalance+=value : 0;
        CRUDConta crudConta = new CRUDConta();
        crudConta.update(this);
        return newValue;
    }

    public abstract void withdrawal(double value);

    public void transferCash(Conta destinatario, double value) {
        withdrawal(value);
        destinatario.deposit(value);
        CRUDConta crudConta = new CRUDConta();
        crudConta.update(this);
    }

    @Override
    public String toString() {
        return "Client:"+getClient().getName() + ", BankNumber:"+getBankNumber() + ", Actual balance:$"+getActualBalance();
    }
}
