package Classes;

import java.util.Date;
import java.util.List;

public class Sale {
    private int id;
    private Date date;
    private List<Product> products;
    private double totalPrice;
    private Client client;

    // Constructor
    public Sale(int id, Date date, List<Product> products, double totalPrice, Client client) {
        this.id = id;
        this.date = date;
        this.products = products;
        this.totalPrice = totalPrice;
        this.client = client;
    }

    // Getter and setter methods for each attribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
