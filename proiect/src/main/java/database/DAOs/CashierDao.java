package database.DAOs;

import Classes.Cashier;
import Classes.Employee;
import database.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CashierDao extends EmployeeDao {

    public CashierDao(Connection connection) {
        super(connection);
    }
    @Override
    public void create(Employee employee) {
        if (employee instanceof Cashier) {
            // Adăugă în tabelul employee
            super.create(employee);

            // Adăugă register_number în tabela cashier
            Cashier cashier = (Cashier) employee;
            addRegisterNumber(cashier.getId(), cashier.getRegisterNumber());
        } else {
            // Poți trata cazul în care încerci să creezi un alt tip de angajat decât Cashier
            // Poți arunca o excepție sau trata altfel situația, în funcție de cerințele tale
        }
    }

    // Metodă pentru a adăuga register_number în tabela cashier
    private void addRegisterNumber(int employeeId, int registerNumber) {
        String query = "UPDATE cashier SET register_number = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, registerNumber);
            statement.setInt(2, employeeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Același principiu se aplică și pentru celelalte metode CRUD
}
