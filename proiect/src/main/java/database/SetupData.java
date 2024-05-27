package database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class SetupData {

    public void createTable() {
        String createTableSql = """
            CREATE TABLE IF NOT EXISTS employee (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(40),
                salary DOUBLE,
                position VARCHAR(40)
            )
            """;

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(String name, double salary, String position) {
        String insertEmployeeSql = String.format(
                "INSERT INTO employee (name, salary, position) VALUES ('%s', %f, '%s')",
                name, salary, position
        );
        Connection connection = DatabaseConfig.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(insertEmployeeSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getAllEmployees() {
        String selectSql = "SELECT * FROM employee";
        Connection connection = DatabaseConfig.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while(resultSet.next()) {
                System.out.println("Id: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Salary: " + resultSet.getDouble("salary"));
                System.out.println("Position: " + resultSet.getString("position"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
