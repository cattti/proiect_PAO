package Collections;

import Classes.Sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesHistory {
    private static List<Sale> salesList = new ArrayList<>();

    // Constructor
    public SalesHistory() {
        this.salesList = new ArrayList<>();
    }

    // Method to add a sale to the sales history
    public static void addSale(Sale sale) {
        salesList.add(sale);
    }

    // Method to get all sales from the sales history
    public static List<Sale> getAllSales() {
        return salesList;
    }

    // Method to search for sales by date
    public List<Sale> searchByDate(Date date) {
        List<Sale> foundSales = new ArrayList<>();
        for (Sale sale : salesList) {
            if (sale.getDate().equals(date)) {
                foundSales.add(sale);
            }
        }
        return foundSales;
    }
    public static Sale getSaleById(int saleId) {
        for (Sale sale : salesList) {
            if (sale.getId() == saleId) {
                return sale;
            }
        }
        return null;
    }
}
