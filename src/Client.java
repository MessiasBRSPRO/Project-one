public class Client {
    //This class represents an person in the bank
    private String name;
    private int age;
    private String cpf;
    public Client(String name, int age, String cpf){
        this.name = name;
        this.age = age;
        this.cpf = CPFValidator.validatorCPF(cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name:"+getName() + ", Age:"+getAge() + ", CPF:"+this.cpf;
    }
}
