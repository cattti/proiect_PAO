package Classes;
import Collections.*;
import Service.Service;
import database.DAOs.CashierDao;
import database.DAOs.EmployeeDao;
import database.DatabaseConfig;
import database.SetupData;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        SetupData setupData = new SetupData();

        setupData.createTables();
        System.out.println("Tabelele au fost create cu succes.");
        try (Connection connection = DatabaseConfig.getDatabaseConnection()) {
            // Creează un nou obiect de tip casier
            Cashier newCashier = new Cashier(0, "John Doe", 2500.0, "Cashier", 123456);

            // Creează un obiect de tip CashierDao pentru a interacționa cu baza de date
            CashierDao cashierDao = new CashierDao(connection);

            // Adaugă casierul în baza de date
            cashierDao.create(newCashier);

            System.out.println("Cashier added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Crearea unei instanțe a clasei Service
        Service service = new Service();

        // Adăugare angajați
        Employee cashier1 = new Cashier(1, "John Doe", 1000.00, "Cashier", 11);
        Employee cashier2 = new Cashier(2, "Alice Smith", 1500.00, "Cashier",12);
        Employee manager = new Manager(3, "Jane Johnson", 3000.00, "Manager", 10);
        EmployeesList.addEmployee(cashier1);
        EmployeesList.addEmployee(cashier2);
        EmployeesList.addEmployee(manager);

        // Adăugare produse
        Category electronics = new Category(1, "Electronics", "Electronics category");
        Product laptop = new Product(11, "Laptop", 1500.0, 10, electronics);
        Product smartphone = new Product(12, "Smartphone", 800.0, 20, electronics);
        Product tv = new Product(13, "Smart TV", 1200.0, 15, electronics);
        Inventory.addProduct(laptop);
        Inventory.addProduct(smartphone);
        Inventory.addProduct(tv);

        Category clothing = new Category(2, "Clothing", "Clothing category");
        Product shirt = new Product(24, "T-Shirt", 25.0, 50, clothing);
        Product pants = new Product(25, "Jeans", 50.0, 30, clothing);
        Inventory.addProduct(shirt);
        Inventory.addProduct(pants);
// Crearea listelor de produse pentru fiecare furnizor
        List<Product> productsFromSupplier1 = new ArrayList<>();
        productsFromSupplier1.add(new Product(1, "New Laptop", 1800.0, 5, electronics));
        productsFromSupplier1.add(new Product(2, "New Smartphone", 1000.0, 10, electronics));

        List<Product> productsFromSupplier2 = new ArrayList<>();
        productsFromSupplier2.add(new Product(3, "Summer Dress", 50.0, 15, clothing));
        productsFromSupplier2.add(new Product(4, "Casual Shirt", 30.0, 20, clothing));

        // Adăugare furnizori
        Supplier supplier1 = new Supplier(1, "Electronics Supplier", "123 Supplier St", "supplier1@example.com", productsFromSupplier1);
        Supplier supplier2 = new Supplier(2, "Clothing Supplier", "456 Supplier St", "supplier2@example.com", productsFromSupplier2);
        service.addSupplier(supplier1);
        service.addSupplier(supplier2);

        // Adăugare clienți
        Client client1 = new Client(1, "Alice Johnson", "123 Main St", "alice@example.com");
        Client client2 = new Client(2, "Bob Smith", "456 Elm St", "bob@example.com");
        service.addClient(client1);
        service.addClient(client2);


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Meniu:");
            System.out.println("1. Cumpărare produse pentru client");
            System.out.println("2. Achiziție produse de la furnizor");
            System.out.println("3. Afișare stoc pentru toate produsele");
            System.out.println("4. Afișare stoc pentru un produs după ID");
            System.out.println("5. Afișare casierului pentru un sale după ID");
            System.out.println("6. Afișare sumă și produse pentru un sale după ID");
            System.out.println("7. Afișare produse cu stoc mai mic de 50");
            System.out.println("8. Adăugare furnizor");
            System.out.println("9. Ieșire");

            System.out.print("Alegeți opțiunea: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumăm linia goală rămasă în buffer

            switch (option) {
                case 1:
                    scanProductsForSale(scanner, service);
                    break;
                case 2:
                    purchaseProductsFromSupplier(service);
                    break;
                case 3:
                    displayInventory(service);
                    break;
                case 4:
                    displayProductStockById(scanner, service);
                    break;
                case 5:
                    displayCashierForSale(scanner, service);
                    break;
                case 6:
                    displaySaleDetails(scanner, service);
                    break;
                case 7:
                    displayProductsUnderThreshold(service, 50);
                    break;
                case 8:
                    addSupplier(scanner, service);
                    break;
                case 9:
                    addEmployee(scanner,service);
                    break;
                case 10:
                    removeEmployee(scanner,service);
                    break;
                case 11:
                    System.out.println("Programul a fost încheiat.");
                    return;
                default:
                    System.out.println("Opțiune invalidă. Vă rugăm să alegeți din nou.");
            }
        }
    }

    private static void scanProductsForSale(Scanner scanner, Service service) {
        // Afișează lista de produse disponibile pentru vânzare
        System.out.println("Produse disponibile pentru vânzare:");
        List<Product> availableProducts = Inventory.getAllProducts();
        for (Product product : availableProducts) {
            System.out.println(product.getId() + ". " + product.getName() + " - " + product.getPrice());
        }

        System.out.println("Casieri disponibili:");
        List<Employee> availableCashiers = EmployeesList.getAllEmployeesByPosition("Cashier");
        for (Employee cashier : availableCashiers) {
            System.out.println(cashier.getId() + ". " + cashier.getName());
        }
        // Crează un nou sale
        int saleId = SalesHistory.getAllSales().size() + 1;
        Date saleDate = new Date();
        List<Product> productsToSell = new ArrayList<>();
        double totalPrice = 0;

        // Introducerea ID-ului casierului care efectuează vânzarea
        System.out.print("Introduceți ID-ul casierului: ");
        int cashierId = scanner.nextInt();
        Employee cashier = EmployeesList.getEmployeeById(cashierId);
        if (cashier == null || cashier.getPosition().equals("Manager")) {
            System.out.println("ID-ul casierului este invalid sau nu este un casier.");
            return;
        }
        // Scanare produse și adăugare în sale
        System.out.println("Scanati produsele (introduceți ID-urile produselor, 0 pentru a finaliza):");
        int productId;
        while (true) {
            System.out.print("Introduceți ID-ul produsului: ");
            productId = scanner.nextInt();
            if (productId == 0) {
                break; // Ieșire din buclă dacă se introduc 0
            }
            boolean found = false;
            for (Product product : availableProducts) {
                if (product.getId() == productId) {
                    productsToSell.add(product);
                    totalPrice += product.getPrice();
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Produsul cu ID-ul " + productId + " nu există.");
            }
        }

        // Verifică dacă s-au scanat produse
        if (!productsToSell.isEmpty()) {
            // Adăugare sale în istoric
            Sale sale = new Sale(saleId, saleDate, productsToSell, totalPrice, null, cashier); // Clientul este null sau anonim
            SalesHistory.addSale(sale);
            // Actualizare stoc
            for (Product product : productsToSell) {
                Inventory.updateProductQuantity(product, -1);
            }

            System.out.println("Produse cumpărate:");
            for (Product product : productsToSell) {
                System.out.println("- " + product.getName() + " - " + product.getPrice());
            }
            System.out.println("Total: $" + totalPrice);

            System.out.println("Vânzarea a fost înregistrată cu succes.");
        } else {
            System.out.println("Nu s-au scanat produse. Vânzarea a fost anulată.");
        }
    }



    private static void purchaseProductsFromSupplier(Service service) {
        // Afișează lista de furnizori disponibili
        List<Supplier> suppliers = service.getAllSuppliers();
        System.out.println("Furnizori disponibili:");
        for (Supplier supplier : suppliers) {
            System.out.println(supplier.getId() + ". " + supplier.getName());
        }

        // Selectează furnizorul
        Scanner scanner = new Scanner(System.in);
        System.out.print("Alegeți furnizorul: ");
        int supplierId = scanner.nextInt();
        scanner.nextLine(); // Consumăm linia goală rămasă în buffer
        Supplier selectedSupplier = null;
        for (Supplier supplier : suppliers) {
            if (supplier.getId() == supplierId) {
                selectedSupplier = supplier;
                break;
            }
        }

        if (selectedSupplier != null) {
            // Afișează lista de produse disponibile de la furnizor
            List<Product> productsFromSupplier = selectedSupplier.getProducts();
            System.out.println("Produse disponibile de la furnizor:");
            for (Product product : productsFromSupplier) {
                System.out.println("- " + product.getId() + ". " + product.getName() + " - " + product.getPrice());
            }

            // Selectează produsele și cantitatea dorită pentru fiecare
            List<Product> selectedProducts = new ArrayList<>();
            System.out.println("Introduceți ID-urile produselor pe care doriți să le achiziționați (separate prin spațiu): ");
            String productIdsInput = scanner.nextLine();
            if (!productIdsInput.isEmpty()) {
                String[] productIds = productIdsInput.split(" ");
                for (String productId : productIds) {
                    int id = Integer.parseInt(productId);
                    for (Product product : productsFromSupplier) {
                        if (product.getId() == id) {
                            selectedProducts.add(product);
                            break;
                        }
                    }
                }

                // Introduceți cantitatea pentru fiecare produs selectat
                for (Product product : selectedProducts) {
                    System.out.print("Introduceți cantitatea pentru produsul " + product.getName() + ": ");
                    int quantity = scanner.nextInt();
                    product.setQuantity(quantity);
                }

                // Calculează totalul prețurilor produselor
                double totalPrice = 0;
                for (Product product : selectedProducts) {
                    totalPrice += product.getPrice() * product.getQuantity();
                }

                // Adaugă produsele achiziționate în inventar și afișează un mesaj de confirmare
                for (Product product : selectedProducts) {
                    Inventory.addProduct(product);
                }
                System.out.println("Produse achiziționate de la furnizorul " + selectedSupplier.getName());
                System.out.println("Total price: $" + totalPrice);
            } else {
                System.out.println("Nu ați introdus niciun ID pentru produs.");
            }
        } else {
            System.out.println("Nu s-a găsit furnizorul selectat.");
        }
    }

    private static void addEmployee(Scanner scanner, Service service) {
        System.out.println("Tipuri de angajați disponibili:");
        System.out.println("1. Casier");
        System.out.println("2. Manager");
        System.out.print("Alegeți tipul de angajat: ");
        int employeeType = scanner.nextInt();
        scanner.nextLine(); // Consumăm linia goală rămasă în buffer

        System.out.print("Introduceți numele angajatului: ");
        String name = scanner.nextLine();

        System.out.print("Introduceți salariul angajatului: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consumăm linia goală rămasă în buffer

        Employee employee;
        if (employeeType == 1) {
            employee = new Cashier(EmployeesList.getNextEmployeeId(), name, salary, "Cashier",13);
        } else if (employeeType == 2) {
            System.out.print("Introduceți numărul de magazine gestionate de manager: ");
            int numberOfStoresManaged = scanner.nextInt();
            scanner.nextLine(); // Consumăm linia goală rămasă în buffer
            employee = new Manager(EmployeesList.getNextEmployeeId(), name, salary, "Manager", numberOfStoresManaged);
        } else {
            System.out.println("Tip de angajat invalid.");
            return;
        }

        EmployeesList.addEmployee(employee);
        System.out.println("Angajatul a fost adăugat cu succes.");
    }

    private static void removeEmployee(Scanner scanner, Service service) {
        System.out.print("Introduceți ID-ul angajatului pe care doriți să-l eliminați: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consumăm linia goală rămasă în buffer

        Employee employeeToRemove = EmployeesList.getEmployeeById(employeeId);

        if (employeeToRemove != null) {
            EmployeesList.removeEmployee(employeeToRemove);
            System.out.println("Angajatul a fost eliminat cu succes.");
        } else {
            System.out.println("Nu există angajat cu ID-ul specificat.");
        }
    }


    private static void addSupplier(Scanner scanner, Service service) {
        System.out.print("Introduceți numele furnizorului: ");
        String name = scanner.nextLine();

        System.out.print("Introduceți adresa furnizorului: ");
        String address = scanner.nextLine();

        System.out.print("Introduceți informațiile de contact ale furnizorului: ");
        String contact = scanner.nextLine();

        // Initializează o listă goală de produse pentru furnizor
        List<Product> products = new ArrayList<>();

        Supplier supplier = new Supplier(service.getAllSuppliers().size() + 1, name, address, contact, products);
        service.addSupplier(supplier);

        System.out.println("Furnizorul a fost adăugat cu succes.");
    }

    private static void displayInventory(Service service) {
        List<Product> inventory = Inventory.getAllProducts();

        System.out.println("Inventory:");
        for (Product product : inventory) {
            System.out.println("ID: " + product.getId() + ", Name: " + product.getName() +
                    ", Price: $" + product.getPrice() + ", Quantity: " + product.getQuantity() +
                    ", Category: " + product.getCategory().getName());
        }
    }

    private static void displayProductStockById(Scanner scanner, Service service) {
        System.out.print("Introduceți ID-ul produsului: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consumă newline

        Product product = Inventory.searchById(productId);
        if (product != null) {
            System.out.println("Product found:");
            System.out.println("Name: " + product.getName() + ", Quantity: " + product.getQuantity());
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

    private static void displayCashierForSale(Scanner scanner, Service service) {
        System.out.print("Introduceți ID-ul saleului: ");
        int saleId = scanner.nextInt();
        scanner.nextLine(); // Consumă newline

        Employee cashier = EmployeesList.getCashierForSale(saleId);
        if (cashier != null) {
            System.out.println("Cashier for sale with ID " + saleId + ": " + cashier.getName());
        } else {
            System.out.println("Sale with ID " + saleId + " not found or no cashier assigned.");
        }
    }
    private static void displaySaleDetails(Scanner scanner, Service service) {
        System.out.print("Introduceți ID-ul vânzării: ");
        int saleId = scanner.nextInt();

        Sale sale = SalesHistory.getSaleById(saleId);
        if (sale != null) {

            System.out.println("Detalii vânzare (ID " + saleId + "):");
            System.out.println("Data: " + sale.getDate());
            System.out.println("Total: $" + sale.getTotalPrice());

            // Afisare produse vandute
            System.out.println("Produse:");
            for (Product product : sale.getProducts()) {
                System.out.println("- " + product.getName() + " - " + product.getPrice());
            }

            // Afisare casier responsabil pentru vanzare
            Cashier cashier = (Cashier) sale.getCashier();
            if (cashier != null) {
                System.out.println("Casier: " + cashier.getName());
            } else {
                System.out.println("Casierul asociat nu a fost găsit.");
            }
        } else {
            System.out.println("Vânzarea cu ID-ul " + saleId + " nu a fost găsită.");
        }
    }

    private static void displayProductsUnderThreshold(Service service, int threshold) {
        List<Product> lowStockProducts = service.getProductsUnderThreshold(threshold);
        if (!lowStockProducts.isEmpty()) {
            System.out.println("Produse cu stoc sub " + threshold + ":");
            for (Product product : lowStockProducts) {
                System.out.println("- " + product.getName() + " - Stoc: " + product.getQuantity());
            }
        } else {
            System.out.println("Nu există produse cu stoc sub " + threshold + ".");
        }
    }



}
