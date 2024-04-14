package Classes;
import Collections.*;
import Service.Service;

import java.util.Date;
import java.util.List;

public class Main {
//    public static void main(String[] args) {
//        // Crearea unei instanțe a clasei Service
//        Service service = new Service();
//
//        // Adăugare angajați
//        Employee employee1 = new Cashier(1, "John Doe", 1000.00,"Cashier");
//        Employee employee2 = new Manager(2, "Jane Smith",3000.00, "Manager",10);
//        service.addEmployee(employee1);
//        service.addEmployee(employee2);
//
//        // Adăugare produse
//        Category category1 = new Category(1, "Electronics", "Electronics category");
//        Product product1 = new Product(1, "Laptop", 1500.0, 10, category1);
//        Product product2 = new Product(2, "Smartphone", 800.0, 20, category1);
//        service.addProduct(product1);
//        service.addProduct(product2);
//
//        // Vânzare de produse
//        Client client = new Client(1, "Alice Johnson", "123 Main St", "alice@example.com");
//        List<Product> productsToSell = service.searchProductsByName("Laptop");
//        double totalPrice = productsToSell.get(0).getPrice();
//        service.sellProducts(1, productsToSell, totalPrice, client);
//
//        // Afisare produse dupa categorie
//        List<Product> electronicsProducts = service.searchProductsByCategory("Electronics");
//        System.out.println("Electronic products:");
//        for (Product product : electronicsProducts) {
//            System.out.println(product.getName());
//        }
//    }








    public static void main(String[] args) {
        // Crearea unei instanțe a clasei Service
        Service service = new Service();

        // Adăugare angajați
        Employee cashier1 = new Cashier(1, "John Doe",1000.00, "Cashier");
        Employee cashier2 = new Cashier(2, "Alice Smith",1500.00, "Cashier");
        Employee manager = new Manager(3, "Jane Johnson", 3000.00,"Manager", 10);
        EmployeesList.addEmployee(cashier1);
        EmployeesList.addEmployee(cashier2);
        EmployeesList.addEmployee(manager);

        // Adăugare produse
        Category electronics = new Category(1, "Electronics", "Electronics category");
        Product laptop = new Product(1, "Laptop", 1500.0, 10, electronics);
        Product smartphone = new Product(2, "Smartphone", 800.0, 20, electronics);
        Product tv = new Product(3, "Smart TV", 1200.0, 15, electronics);
        Inventory.addProduct(laptop);
        Inventory.addProduct(smartphone);
        Inventory.addProduct(tv);

        Category clothing = new Category(2, "Clothing", "Clothing category");
        Product shirt = new Product(4, "T-Shirt", 25.0, 50, clothing);
        Product pants = new Product(5, "Jeans", 50.0, 30, clothing);
        Inventory.addProduct(shirt);
        Inventory.addProduct(pants);

        // Adăugare furnizori
        Supplier supplier1 = new Supplier(1, "Electronics Supplier", "123 Supplier St", "supplier1@example.com");
        Supplier supplier2 = new Supplier(2, "Clothing Supplier", "456 Supplier St", "supplier2@example.com");


        // Adăugare clienți
        Client client1 = new Client(1, "Alice Johnson", "123 Main St", "alice@example.com");
        Client client2 = new Client(2, "Bob Smith", "456 Elm St", "bob@example.com");


        // Vânzare de produse
        List<Product> productsToSell = Inventory.getAllProducts();
        double totalPrice = 0;
        for (Product product : productsToSell) {
            totalPrice += product.getPrice() * 2; // Vânzăm câte două bucăți din fiecare produs
        }
        Sale sale = new Sale(1, new Date(), productsToSell, totalPrice, client1);
        SalesHistory.addSale(sale);

        // Afișare istoric vânzări
        List<Sale> salesHistory = SalesHistory.getAllSales();
        System.out.println("Sales History:");
        for (Sale s : salesHistory) {
            System.out.println("Sale ID: " + s.getId() + ", Date: " + s.getDate() + ", Total Price: $" + s.getTotalPrice());
        }
    }
}
