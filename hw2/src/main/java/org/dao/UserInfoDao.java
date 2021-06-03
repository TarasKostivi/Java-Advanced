package org.dao;

import org.config.PostgresConnector;
import org.model.Address;
import org.model.People;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDao {

    public List<People> getPeopleInfo(){
        List<People> peopleList = new ArrayList<>();
        try (Connection connection = PostgresConnector.getConnection();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM peopleses");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");

                People people = People.builder()
                        .id(id)
                        .name(name)
                        .surname(surname)
                        .age(age)
                        .build();
                peopleList.add(people);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peopleList;
    }

    public void updatePeoples(int id, People people){
        String query = "UPDATE peopleses SET name = ?, surname = ?,  age = ? WHERE id = ? ";

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)){

            prepareStatement.setString(1, people.getName());
            prepareStatement.setString(2, people.getSurname());
            prepareStatement.setInt(3, people.getAge());
            prepareStatement.setInt(4, people.getId());


            if (prepareStatement.executeUpdate() > 1) {
                throw new RuntimeException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletePeoples(People people){
        String query = "DELETE FROM peopleses WHERE name = ?  ";

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)){

            prepareStatement.setString(1, people.getName());
            prepareStatement.setString(2, people.getSurname());
            prepareStatement.setInt(3, people.getAge());


            if (prepareStatement.executeUpdate() > 1) {
                throw new RuntimeException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(People people){
        String query = "INSERT INTO peopleses (name, surname, age) VALUES(?,?,?)";

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query)){

            prepareStatement.setString(1, people.getName());
            prepareStatement.setString(2, people.getSurname());
            prepareStatement.setInt(3, people.getAge());

            if (prepareStatement.executeUpdate() > 1) {
                throw new RuntimeException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<People> getPeopleBySurname(String surname, int id){
        List<People> peopleList = new ArrayList<>();
        String query = "SELECT * FROM peopleses WHERE surname = ? and id = ?";

        try (Connection connection = PostgresConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, surname);
            statement.setInt(2, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname1 = resultSet.getString("surname");
                int age = resultSet.getInt("age");

                People people = People.builder()
                        .id(id)
                        .name(name)
                        .surname(surname)
                        .age(age)
                        .build();
                peopleList.add(people);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peopleList;
    }

    public List<Address> getAddressInfo(){
        List<Address> addressList = new ArrayList<>();
        try (Connection connection = PostgresConnector.getConnection();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM adres_people");

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String adress = resultSet.getString("adress");

                Address address = Address.builder()
                        .id(id)
                        .address(adress)
                        .build();
                addressList.add(address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressList;
    }
}
