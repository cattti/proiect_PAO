package Collections;

import Classes.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeesList {
    private static List<Employee> employees = new ArrayList<>();

    // Constructor
    public EmployeesList() {
        this.employees = new ArrayList<>();
    }

    // Method to add an employee to the list
    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Method to remove an employee from the list
    public static void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    // Method to get all employees from the list
    public List<Employee> getAllEmployees() {
        return employees;
    }
}
