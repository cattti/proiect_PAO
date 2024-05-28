package database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class SetupData {



    public void createTables() {
        String[] createTableSqls = {
                """
            CREATE TABLE IF NOT EXISTS department (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(40) NOT NULL
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS employee (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(40) NOT NULL,
                salary DOUBLE NOT NULL,
                position VARCHAR(40) NOT NULL,
                department_id INT,
                FOREIGN KEY (department_id) REFERENCES department(id)
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS cashier (
                id INT PRIMARY KEY,
                register_number INT NOT NULL,
                FOREIGN KEY (id) REFERENCES employee(id)
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS manager (
                id INT PRIMARY KEY,
                bonus_percentage DOUBLE NOT NULL,
                FOREIGN KEY (id) REFERENCES employee(id)
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS category (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(40) NOT NULL,
                description TEXT
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS product (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(40) NOT NULL,
                price DOUBLE NOT NULL,
                quantity INT NOT NULL,
                category_id INT,
                FOREIGN KEY (category_id) REFERENCES category(id)
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS supplier (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(40) NOT NULL,
                address VARCHAR(100),
                contact VARCHAR(40)
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS sale (
                id INT PRIMARY KEY AUTO_INCREMENT,
                sale_date DATE NOT NULL,
                total_price DOUBLE NOT NULL,
                client_id INT,
                cashier_id INT,
                FOREIGN KEY (cashier_id) REFERENCES employee(id)
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS store (
                name VARCHAR(40) PRIMARY KEY,
                location VARCHAR(100) NOT NULL
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS sale_product (
                sale_id INT,
                product_id INT,
                PRIMARY KEY (sale_id, product_id),
                FOREIGN KEY (sale_id) REFERENCES sale(id),
                FOREIGN KEY (product_id) REFERENCES product(id)
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS supplier_product (
                supplier_id INT,
                product_id INT,
                PRIMARY KEY (supplier_id, product_id),
                FOREIGN KEY (supplier_id) REFERENCES supplier(id),
                FOREIGN KEY (product_id) REFERENCES product(id)
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS store_employee (
                store_name VARCHAR(40),
                employee_id INT,
                PRIMARY KEY (store_name, employee_id),
                FOREIGN KEY (store_name) REFERENCES store(name),
                FOREIGN KEY (employee_id) REFERENCES employee(id)
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS store_product (
                store_name VARCHAR(40),
                product_id INT,
                PRIMARY KEY (store_name, product_id),
                FOREIGN KEY (store_name) REFERENCES store(name),
                FOREIGN KEY (product_id) REFERENCES product(id)
            )
            """,
                """
            CREATE TABLE IF NOT EXISTS store_sale (
                store_name VARCHAR(40),
                sale_id INT,
                PRIMARY KEY (store_name, sale_id),
                FOREIGN KEY (store_name) REFERENCES store(name),
                FOREIGN KEY (sale_id) REFERENCES sale(id)
            )
            """
        };

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try {
            Statement statement = connection.createStatement();
            for (String createTableSql : createTableSqls) {
                statement.execute(createTableSql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
