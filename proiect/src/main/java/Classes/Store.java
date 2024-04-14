package Classes;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private String name;
    private String location;
    private List<Employee> employees;
    private List<Product> products;
    private List<Sale> sales;

    // Constructor
    public Store(String name, String location) {
        this.name = name;
        this.location = location;
        this.employees = new ArrayList<>();
        this.products = new ArrayList<>();
        this.sales = new ArrayList<>();
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Sale> getSales() {
        return sales;
    }

    // Method to add an employee to the store
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Method to remove an employee from the store
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    // Method to add a product to the store's inventory
    public void addProduct(Product product) {
        products.add(product);
    }

    // Method to remove a product from the store's inventory
    public void removeProduct(Product product) {
        products.remove(product);
    }

    // Method to add a sale to the store's sales history
    public void addSale(Sale sale) {
        sales.add(sale);
    }
}
