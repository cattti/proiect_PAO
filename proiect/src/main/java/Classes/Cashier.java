package Classes;

public class Cashier extends Employee{
    private int registerNumber;

    public Cashier(int id, String name, double salary, String position, int registerNumber) {
        super(id, name, salary, position);
        this.registerNumber= registerNumber;
    }


    public int getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(int registerNumber) {
        this.registerNumber = registerNumber;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", salary=" + getSalary() +
                ", position='" + getPosition() + '\'' +
                ", registerNumber=" + registerNumber +
                '}';
    }
}
