package Classes;

public class Manager extends Employee{
    private double bonusPercentage;

    public Manager(int id, String name, double salary, String position, double bonusPercentage) {
        super(id, name, salary, position);
        this.bonusPercentage = bonusPercentage;
    }

    // Getter and setter methods for bonusPercentage
    public double getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(double bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }
}
