package bts.dao;

import bts.config.PostgresConnector;
import bts.model.Role;
import bts.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoJDBCImpl implements ProductDao {

    @Override
    public List<Product> findAll() {
        String query = "SELECT * FROM product";
        List<Product> products = new ArrayList<>();

        try (Connection connection = PostgresConnector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Optional<Product> findById(int id) {
        String query = "SELECT * FROM product WHERE id =?";
        List<Product> products = new ArrayList<>();

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")
                );
                return Optional.of(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public  void updateProduct(Product product){
        String query = "UPDATE product SET  name = ? WHERE id = ?";

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  void save(Product product){
        String query = "INSERT INTO product (name, description, price) VALUES (?,?,?)";

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
