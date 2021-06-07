package bts.dao;

import bts.config.PostgresConnector;
import bts.model.Role;
import bts.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJDBCImpl implements UserDao {

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM usr";
        List<User> users = new ArrayList<>();

        try (Connection connection = PostgresConnector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                users.add(parseFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> findById(int id) {
        String query = "SELECT * FROM usr WHERE id =?";
        List<User> users = new ArrayList<>();

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return Optional.of(parseFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "SELECT * FROM usr WHERE email =?";
        List<User> users = new ArrayList<>();

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return Optional.of(parseFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public  void save(User user){
        String query = "INSERT INTO usr (email, password, name, role) VALUES (?,?,?,?)";

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getRole().name());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User parseFromResultSet(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                Role.valueOf(resultSet.getString("role"))
        );
    }
}
