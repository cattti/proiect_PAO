package database.DAOs;

import Classes.Cashier;
import Classes.Employee;
import database.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CashierDao extends EmployeeDao {

    public CashierDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Employee employee) {
        if (employee instanceof Cashier) {
            Cashier cashier = (Cashier) employee;

            try {
                connection.setAutoCommit(false);

                // Adăugă în tabelul employee
                super.create(employee);

                // Adăugă în tabelul cashier
                String query = "INSERT INTO cashier (id, register_number) VALUES (?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, cashier.getId());
                    statement.setInt(2, cashier.getRegisterNumber());
                    statement.executeUpdate();
                }

                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Poți trata cazul în care încerci să creezi un alt tip de angajat decât Cashier
            // Poți arunca o excepție sau trata altfel situația, în funcție de cerințele tale
        }
    }
    @Override
    public Cashier read(int id) {
        String query = "SELECT e.id, e.name, e.salary, e.position, c.register_number " +
                "FROM employee e " +
                "JOIN cashier c ON e.id = c.id " +
                "WHERE e.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Cashier(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("position"),
                        resultSet.getInt("register_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @Override
//    public List<Cashier> readAll() {
//        String query = "SELECT e.id, e.name, e.salary, e.position, c.register_number " +
//                "FROM employee e " +
//                "JOIN cashier c ON e.id = c.id";
//        List<Cashier> cashiers = new ArrayList<>();
//        try (PreparedStatement statement = connection.prepareStatement(query);
//             ResultSet resultSet = statement.executeQuery()) {
//            while (resultSet.next()) {
//                cashiers.add(new Cashier(
//                        resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        resultSet.getDouble("salary"),
//                        resultSet.getString("position"),
//                        resultSet.getInt("register_number")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return cashiers;
//    }

    @Override
    public void update(Employee employee) {
        if (employee instanceof Cashier) {
            Cashier cashier = (Cashier) employee;

            try {
                connection.setAutoCommit(false);

                // Actualizează tabelul employee
                super.update(employee);

                // Actualizează tabelul cashier
                String query = "UPDATE cashier SET register_number = ? WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, cashier.getRegisterNumber());
                    statement.setInt(2, cashier.getId());
                    statement.executeUpdate();
                }

                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Poți trata cazul în care încerci să actualizezi un alt tip de angajat decât Cashier
            // Poți arunca o excepție sau trata altfel situația, în funcție de cerințele tale
        }
    }

    @Override
    public void delete(int id) {
        try {
            connection.setAutoCommit(false);

            // Șterge din tabelul cashier
            String queryCashier = "DELETE FROM cashier WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(queryCashier)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

            // Șterge din tabelul employee
            super.delete(id);

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
