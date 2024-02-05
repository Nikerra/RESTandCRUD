package org.example.repository;

import org.example.model.Car;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface CarRepository extends Repository<Car, String> {
    Set<Car> findByModel(String model) throws SQLException;
    Optional<Car> findById(Long id) throws SQLException;
    Boolean deleteByModel(String model) throws SQLException;

}
