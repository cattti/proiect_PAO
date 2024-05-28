package database.DAOs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    protected Connection connection;

    public GenericDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(T t) {
    }

    @Override
    public T read(int id) {
        return null;
    }

    @Override
    public List<T> readAll() {
        return null;
    }

    @Override
    public void update(T t) {
    }

    @Override
    public void delete(int id) {
    }

    protected void closeResources(PreparedStatement statement, ResultSet resultSet) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
