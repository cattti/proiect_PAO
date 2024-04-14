package Collections;

import Classes.Employee;
import Classes.Sale;

import java.util.ArrayList;
import java.util.List;

public class EmployeesList {
    private static List<Employee> employees = new ArrayList<>();
    private static int nextEmployeeId = 1;

    // Constructor
    public EmployeesList() {
        this.employees = new ArrayList<>();
    }

    // Method to add an employee to the list
    public static void addEmployee(Employee employee) {
        employees.add(employee);
        nextEmployeeId++;
    }
    // Method to remove an employee from the list
    public static void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public static Employee getEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }
    public static int getNextEmployeeId() {
        return nextEmployeeId;
    }
    public List<Employee> getAllEmployees() {
        return employees;
    }

    public static Employee getCashierForSale(int saleId) {
        for (Sale sale : SalesHistory.getAllSales()) {
            if (sale.getId() == saleId) {
                return sale.getCashier();
            }
        }
        return null; // Return null if saleId not found or no cashier assigned
    }
    public static List<Employee> getAllEmployeesByPosition(String position) {
        List<Employee> employeesByPosition = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getPosition().equalsIgnoreCase(position)) {
                employeesByPosition.add(employee);
            }
        }
        return employeesByPosition;
    }
}
