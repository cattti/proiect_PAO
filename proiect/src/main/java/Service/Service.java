package Service;

import Collections.EmployeesList;
import Collections.Inventory;
import Collections.SalesHistory;

import java.util.Date;

public class Service {
    private Inventory inventory;
    private SalesHistory salesHistory;
    private EmployeesList employeesList;

    public Service() {
        inventory = new Inventory();
        salesHistory = new SalesHistory();
        employeesList = new EmployeesList();
    }

    // methods to perform operations such as addProduct, removeProduct, makeSale, etc.
}

