package Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.List;

import Classes.*;
import Collections.EmployeesList;
import Collections.SalesHistory;
import Collections.Inventory;

public class Service {
    private Inventory inventory;
    private List<Category> categories;
    private List<Client> clients;
    private List<Supplier> suppliers;

    // Constructor
    public Service() {
        this.inventory = new Inventory();
        this.categories = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.suppliers = new ArrayList<>();

    }


    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    public void removeSupplier(Supplier supplier) {
        suppliers.remove(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return suppliers;
    }



    // Method to add a client
    public void addClient(Client client) {
        clients.add(client);
    }

    // Method to remove a client
    public void removeClient(Client client) {
        clients.remove(client);
    }

    // Method to get all clients
    public List<Client> getAllClients() {
        return clients;
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


    public void sellProducts(int id, List<Product> products, double totalPrice, Client client, Cashier cashier) {
        Date saleDate = new Date();
        Sale sale = new Sale(id, saleDate, products, totalPrice, client, cashier);
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
    public List<Product> getProductsUnderThreshold(int threshold) {
        List<Product> lowStockProducts = new ArrayList<>();
        List<Product> allProducts = Inventory.getAllProducts();
        for (Product product : allProducts) {
            if (product.getQuantity() < threshold) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }

}
