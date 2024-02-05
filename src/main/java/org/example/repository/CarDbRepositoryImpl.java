package org.example.repository;

import org.example.dbconnection.PostgresqlDB;
import org.example.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CarDbRepositoryImpl implements CarRepository {
    private Connection connection;
    private static final String CREATE_CAR_SQL = "INSERT INTO car (model) VALUES (?)";
    private static final String UPDATE_CAR_SQL = "UPDATE car SET model = ? WHERE id = ?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";
    private static final String SELECT_CAR_BY_MODEL = "SELECT * FROM car WHERE model = ?";
    private static final String DELETE_CAR_DELETE_BY_MODEL = "delete from car where model = ?";
    private static final String SELECT_COUNT_FROM_ID = "SELECT COUNT(*) FROM car where id = ?";
    private static final String SELECT_COUNT_FROM_MODEL = "SELECT COUNT(*) FROM car where model = ?";

    private PreparedStatement createPreStmt;
    private PreparedStatement updatePreStmt;
    private PreparedStatement findByIdPreStmt;
    private PreparedStatement findByModelPreStmt;
    private PreparedStatement deleteCarById;

    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
        this.findByModelPreStmt = connection.prepareStatement(SELECT_CAR_BY_MODEL);
        this.deleteCarById = connection.prepareStatement(DELETE_CAR_DELETE_BY_MODEL);
    }

    public CarDbRepositoryImpl() {

    }
    public CarRepository initConnection() {
        PostgresqlDB postgresqlDB = new PostgresqlDB();
        CarRepository carRepository;
        try {
            Class.forName("org.postgresql.Driver");
            carRepository = new CarDbRepositoryImpl(postgresqlDB.getConnection());

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return carRepository;
    }
    @Override
    public Car createOrUpdate(Car car) throws SQLException {
        Set<Car> optCar = findByModel(car.getModel());

        if (optCar.isEmpty()) {
            if (car.getId() == null) {
                createPreStmt.setString(1, car.getModel());
                createPreStmt.executeUpdate();
            } else {
                updatePreStmt.setString(1, car.getModel());
                updatePreStmt.setLong(2, car.getId());
                updatePreStmt.executeUpdate();
            }
        }
        return car;
    }

    @Override
    public Optional<Car> findById(Long id) throws SQLException {

        int rowsCount = countRowsById(id);

        if (rowsCount > 1) {
            throw new RuntimeException("Car with id = " + id + " was found many times (" + rowsCount + ").");
        } else if (rowsCount == 0) {
            return Optional.empty();
        }

        findByIdPreStmt.setLong(1, id);
        ResultSet resultSet = findByIdPreStmt.executeQuery();

        resultSet.next();
        Car car = new Car(resultSet.getLong(1), resultSet.getString(2));
        return Optional.of(car);
    }

    @Override
    public Set<Car> findByModel(String model) throws SQLException {

        int rowsCount = countRowsByModel(model);

        if (rowsCount > 1) {
            throw new RuntimeException("Car with model = " + model + " was found many times (" + rowsCount + ").");
        } else if (rowsCount == 0) {
            return Set.of();
        }

        findByModelPreStmt.setString(1, model);
        ResultSet resultSet = findByModelPreStmt.executeQuery();
        resultSet.next();
        Car car = new Car(resultSet.getLong(1), resultSet.getString(2));
        return Set.of(car);
    }

    @Override
    public Boolean deleteByModel(String model) throws SQLException {

        deleteCarById.setString(1, model);
        int affectedRows = deleteCarById.executeUpdate();
        return affectedRows != 0;
    }

    private int countRowsById(Long id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNT_FROM_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowCount = 0;

        while (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    private int countRowsByModel(String model) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNT_FROM_MODEL);
        preparedStatement.setString(1, model);
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowCount = 0;

        while (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }
}
