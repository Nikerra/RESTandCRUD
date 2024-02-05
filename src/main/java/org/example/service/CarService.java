package org.example.service;

import org.example.model.Car;

import java.sql.SQLException;
import java.util.Set;

public interface CarService {

    void addCar(String model) throws SQLException;

    void editModel(Long id, String model) throws SQLException;

    boolean deleteCar(String model) throws SQLException;
    Set<Car> findByModel(String model) throws SQLException;

}
