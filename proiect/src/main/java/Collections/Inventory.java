package Collections;

import java.util.ArrayList;
import java.util.List;

import Classes.Category;
import Classes.Product;

public class Inventory {
    private static List<Product> products = new ArrayList<>();

    // Constructor
    public Inventory() {
        this.products = new ArrayList<>();
    }

    // Method to add a product to the inventory
    public static void addProduct(Product product) {
        products.add(product);
    }

    // Method to remove a product from the inventory
    public void removeProduct(Product product) {
        products.remove(product);
    }

    // Method to update a product in the inventory
    public void updateProduct(Product oldProduct, Product newProduct) {
        int index = products.indexOf(oldProduct);
        if (index != -1) {
            products.set(index, newProduct);
        }
    }

    // Method to search for products by name
    public List<Product> searchByName(String name) {
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    // Method to search for products by category
    public List<Product> searchByCategory(String category) {
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    // Method to get all products from the inventory
    public static List<Product> getAllProducts() {
        return products;
    }

    public void updateProductQuantity(Product product, int quantityChange) {
        for (Product p : products) {
            if (p.equals(product)) {
                int newQuantity = p.getQuantity() + quantityChange;
                p.setQuantity(newQuantity);
                break;
            }
        }
    }
}
