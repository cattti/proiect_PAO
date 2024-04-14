package Classes;

import java.util.List;

public class Supplier {
    private int id;
    private String name;
    private String address;
    private String contact;
    public List<Product> products;
    public Supplier(int id, String name, String address, String contact,List<Product> products ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.products = products;

    }

    public List<Product> getProducts() {
        return products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
