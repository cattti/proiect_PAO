package Classes;

public class Cashier extends Employee{
    private int registerNumber;

    public Cashier(int id, String name, double salary, String position) {
        super(id, name, salary, position);
        this.registerNumber= registerNumber;
    }


    // Getter and setter methods for registerNumber
    public int getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(int registerNumber) {
        this.registerNumber = registerNumber;
    }
}