package database.DAOs;

import Classes.Cashier;
import Classes.Employee;
import database.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public abstract class EmployeeDao extends GenericDaoImpl<Employee> {

    private static EmployeeDao instance;

//    protected EmployeeDao(Connection connection) {
//        super(connection);
//    }
//
//    public static EmployeeDao getInstance() {
//        if (instance == null) {
//            instance = new EmployeeDao(DatabaseConfig.getDatabaseConnection());
//        }
//        return instance;
//    }
EmployeeDao(Connection connection) {
    super(DatabaseConfig.getDatabaseConnection());
}

//    public static EmployeeDao getInstance() {
//        if (instance == null) {
//            instance = new EmployeeDao();
//        }
//        return instance;
//    }

    @Override
    public void create(Employee employee) {
        String query = "INSERT INTO employee (name, salary, position) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, employee.getName());
            statement.setDouble(2, employee.getSalary());
            statement.setString(3, employee.getPosition());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                employee.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee read(int id) {
        String query = "SELECT * FROM employee WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("position")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> readAll() {
        String query = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("position")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void update(Employee employee) {
        String query = "UPDATE employee SET name = ?, salary = ?, position = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getName());
            statement.setDouble(2, employee.getSalary());
            statement.setString(3, employee.getPosition());
            statement.setInt(4, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM employee WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Cashier cashier) {
    }
}
