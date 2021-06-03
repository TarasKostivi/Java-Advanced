package bts.dao;

import bts.config.PostgresConnector;
import bts.model.Bucket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BucketDaoJDBCImpl implements BucketDao {
    
    @Override
    public Optional<Bucket> findById(int id) {
        String query = "SELECT * FROM bucket WHERE id =?";
        List<Bucket> buckets = new ArrayList<>();

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                Bucket bucket = new Bucket(
                        resultSet.getInt("id"),
                        resultSet.getDate("purchase_date").toLocalDate()
                );
                return Optional.of(bucket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public  void save(Bucket bucket){
        String query = "INSERT INTO bucket (id, purchase_date) VALUES (?,?)";

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, bucket.getId());
            statement.setDate(2, Date.valueOf(bucket.getPurchaseDate()));

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
