public class Client {
    //This class represents an person in the bank
    private String name;
    private int age;
    private String myCpf;

    public Client(String name, int age, String myCpf){
        this.name = name;
        this.age = age;
        this.myCpf = myCpf;
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
        return "Name:"+getName() + ", Age:"+getAge() + ", CPF:"+this.myCpf;
    }
}
