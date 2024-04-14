package Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.List;
import Classes.Employee;
import Classes.Product;
import Classes.Category;
import Classes.Supplier;
import Classes.Client;
import Classes.Sale;
import Collections.EmployeesList;
import Collections.SalesHistory;
import Collections.Inventory;

public class Service {
    private Inventory inventory;
    private List<Category> categories;

    // Constructor
    public Service() {
        this.inventory = new Inventory();
        this.categories = new ArrayList<>();

    }

    public void addEmployee(Employee employee) {
        EmployeesList.addEmployee(employee);
    }

    public void removeEmployee(Employee employee) {
        EmployeesList.removeEmployee(employee);
    }

    public void addProduct(Product product) {
        inventory.addProduct(product);
    }

    public void removeProduct(Product product) {
        inventory.removeProduct(product);
    }


    public void sellProducts(int id, List<Product> products, double totalPrice, Client client) {
        Date saleDate = new Date();
        Sale sale = new Sale(id, saleDate, products, totalPrice, client);
        SalesHistory.addSale(sale);
        for (Product product : products) {
            inventory.updateProductQuantity(product, -1); // Scădere cantitate
        }
    }

    public List<Product> searchProductsByName(String name) {
        return inventory.searchByName(name);
    }

    public List<Product> searchProductsByCategory(String category) {
        return inventory.searchByCategory(category);
    }

    public void updateProduct(Product oldProduct, Product newProduct) {
        inventory.updateProduct(oldProduct, newProduct);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }
}
