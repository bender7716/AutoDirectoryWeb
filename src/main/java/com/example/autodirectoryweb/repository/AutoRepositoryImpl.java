package com.example.autodirectoryweb.repository;

import com.example.autodirectoryweb.entity.Auto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AutoRepositoryImpl implements AutoRepository {
    @Value( "${spring.datasource.url}" )
    private String jdbcUrl;
    @Value( "${spring.datasource.driver-class-name}" )
    private String jdbcClassDriver;
    @Value( "${spring.datasource.username}" )
    private String jdbcUsername;
    @Value( "${spring.datasource.password}" )
    private String jdbcPassword;
    @Override
    public List<Auto> findAll() {

        String SQL_QUERY = "SELECT * FROM auto";
        List<Auto> autoList = new ArrayList<>();

        try {
            Class.forName(jdbcClassDriver);
            try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_QUERY);
                while (resultSet.next()) {
                    Auto auto = new Auto();
                    auto.setId(resultSet.getLong("id"));
                    auto.setBrand(resultSet.getString("brand"));
                    auto.setModel(resultSet.getString("model"));
                    auto.setCategory(resultSet.getString("category"));
                    auto.setType(resultSet.getString("type"));
                    auto.setRegistrationNumber(resultSet.getString("registration_number"));
                    auto.setManufacturingYear(resultSet.getInt("manufacturing_year"));
                    auto.setCarTrailer(resultSet.getBoolean("car_trailer"));
                    autoList.add(auto);
                }
                resultSet.close();
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return autoList;
    }

    @Override
    public Auto findById(Long id) {

        String SQL_QUERY = "SELECT * FROM auto WHERE id = ?";
        Auto auto = new Auto();

        try {
            Class.forName(jdbcClassDriver);
            try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {

                PreparedStatement statement = connection.prepareStatement(SQL_QUERY);
                statement.setLong(1, id);

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    auto.setId(resultSet.getLong("id"));
                    auto.setBrand(resultSet.getString("brand"));
                    auto.setModel(resultSet.getString("model"));
                    auto.setCategory(resultSet.getString("category"));
                    auto.setType(resultSet.getString("type"));
                    auto.setRegistrationNumber(resultSet.getString("registration_number"));
                    auto.setManufacturingYear(resultSet.getInt("manufacturing_year"));
                    auto.setCarTrailer(resultSet.getBoolean("car_trailer"));
                }
                resultSet.close();
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return auto;
    }

    @Override
    public void create(Auto auto) {

        String SQL_QUERY = "INSERT INTO auto (brand, model, category, type, registration_number, manufacturing_year, car_trailer) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Class.forName(jdbcClassDriver);
            try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {

                PreparedStatement statement = connection.prepareStatement(SQL_QUERY);
                statement.setString(1, auto.getBrand());
                statement.setString(2, auto.getModel());
                statement.setString(3, auto.getCategory());
                statement.setString(4, auto.getType());
                statement.setString(5, auto.getRegistrationNumber());
                statement.setInt(6, auto.getManufacturingYear());
                statement.setBoolean(7, auto.isCarTrailer());

                statement.executeUpdate();

                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Auto auto) {

        String SQL_QUERY = "UPDATE auto SET brand = ?, model = ?, category = ?, type = ?, registration_number = ?, manufacturing_year = ?, car_trailer = ? WHERE id = ?;";

        try {
            Class.forName(jdbcClassDriver);
            try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {

                PreparedStatement statement = connection.prepareStatement(SQL_QUERY);
                statement.setString(1, auto.getBrand());
                statement.setString(2, auto.getModel());
                statement.setString(3, auto.getCategory());
                statement.setString(4, auto.getType());
                statement.setString(5, auto.getRegistrationNumber());
                statement.setInt(6, auto.getManufacturingYear());
                statement.setBoolean(7, auto.isCarTrailer());
                statement.setLong(8, id);

                statement.executeUpdate();

                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {

        String SQL_QUERY = "DELETE FROM auto WHERE id = ?";

        try {
            Class.forName(jdbcClassDriver);
            try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {

                PreparedStatement statement = connection.prepareStatement(SQL_QUERY);
                statement.setLong(1, id);

                statement.executeUpdate();

                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Auto findByRegistrationNumber(String number) {
        String SQL_QUERY = "SELECT * FROM auto WHERE registration_number = ?";
        Auto auto = new Auto();

        try {
            Class.forName(jdbcClassDriver);
            try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {

                PreparedStatement statement = connection.prepareStatement(SQL_QUERY);
                statement.setString(1, number);

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    auto.setId(resultSet.getLong("id"));
                    auto.setBrand(resultSet.getString("brand"));
                    auto.setModel(resultSet.getString("model"));
                    auto.setCategory(resultSet.getString("category"));
                    auto.setType(resultSet.getString("type"));
                    auto.setRegistrationNumber(resultSet.getString("registration_number"));
                    auto.setManufacturingYear(resultSet.getInt("manufacturing_year"));
                    auto.setCarTrailer(resultSet.getBoolean("car_trailer"));
                }
                resultSet.close();
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return auto;
    }
}
