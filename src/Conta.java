public class Conta implements OperationsBank {
    //This is a abstract class than will be used for other son-class

    private Client client;
    private int bankNumber;
    private double actualBalance;


    public Conta(Client client, int bankNumber){
        this.client = client;
        this.bankNumber = bankNumber;
        this.actualBalance = 0;

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

    @Override
    public double deposit(double value) {
        if(value > 0){
            return this.actualBalance += value;
        }
        return 0;
    }

    @Override
    public double withdrawal(double value) {
        if(!(actualBalance >= value)){
            throw new BankOperationException("The actual balance value is small than value of withdrawal");
        }
        return actualBalance -= value;
    }

    @Override
    public void transferCash(Conta destinatario, double value) {
        withdrawal(value); // removing this quantity of the account will be transfer
        destinatario.deposit(value); // transfering a value to destinatario
    }
}
