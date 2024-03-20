public interface OperationsBank {
    double deposit(double value);
    double withdrawal(double value);
    void transferCash(Conta destinatario, double value);
}
