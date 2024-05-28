package database.DAOs;

import Classes.Store;
import database.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreDao extends GenericDaoImpl<Store> {

    public StoreDao(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Store store) {
        String query = "INSERT INTO store (name, location) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, store.getName());
            statement.setString(2, store.getLocation());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Store read(String name, String location) {
        String query = "SELECT * FROM store WHERE name = ? AND location = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, location);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Store(
                        resultSet.getString("name"),
                        resultSet.getString("location")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Store> readAll() {
        List<Store> stores = new ArrayList<>();
        String query = "SELECT * FROM store";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                stores.add(new Store(
                        resultSet.getString("name"),
                        resultSet.getString("location")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stores;
    }

    @Override
    public void update(Store store) {
        String query = "UPDATE store SET location = ? WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, store.getLocation());
            statement.setString(2, store.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(Store store) {
        String query = "DELETE FROM store WHERE name = ? AND location = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, store.getName());
            statement.setString(2, store.getLocation());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
