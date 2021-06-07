package bts.dao.jdbc;

import bts.config.PostgresConnector;
import bts.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BucketProductDao {

    public void add(int bucket, int product){
        String query = "INSERT INTO buck_product VALUES (?,?)";

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, bucket);
            statement.setInt(2, product);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(int bucket, int  product) {
        String query = "DELETE FROM buck_product WHERE bucket_id = ? AND product_id = ?";

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, bucket);
            statement.setInt(2, product);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeByBucket(int bucket) {
        String query = "DELETE FROM buck_product WHERE bucket_id = ?";

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, bucket);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> findProductByBucketId (int bucketId){
        String query = "SELECT p.id, p.name, p.price, p.description FROM product p" +
                " JOIN buck_product bp ON p.id = bp.product_id" +
                " WHERE bp.bucket_id = ?";
        List<Product> products = new ArrayList<>();

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, bucketId);
            ResultSet resultSet = statement.executeQuery();

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
}
