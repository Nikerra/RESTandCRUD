package org.example.repository;


import org.example.dbconnection.PostgresqlDB;
import org.example.model.Car;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class CarDbRepositoryImplTest {


    @org.junit.jupiter.api.Test
    void createOrUpdate() {
        try {
            Car car = new Car( "Lada");
            CarRepository carRepository = new CarDbRepositoryImpl(PostgresqlDB.getConnection());
            assertEquals(car, carRepository.createOrUpdate(car));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void findById() {
        try {
            Car car = new Car( 29L,"Lada");
            CarRepository carRepository = new CarDbRepositoryImpl(PostgresqlDB.getConnection());
            carRepository.createOrUpdate(car);
            assertEquals(Optional.empty(), carRepository.findById(100L));
            assertEquals(Optional.of(car), carRepository.findById(29L));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void findByModel() {
        try {
            Car car = new Car(29L, "Lada");
            CarRepository carRepository = new CarDbRepositoryImpl(PostgresqlDB.getConnection());
            carRepository.createOrUpdate(car);
            assertEquals(Set.of(), carRepository.findByModel("BMW"));
            assertEquals(Set.of(car), carRepository.findByModel("Lada"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void deleteModel() {
        try {
            Car car = new Car( "Toyota");
            CarRepository carRepository = new CarDbRepositoryImpl(PostgresqlDB.getConnection());
            carRepository.createOrUpdate(car);
            assertTrue(carRepository.deleteByModel("Toyota"));
            assertFalse(carRepository.deleteByModel("Toyota"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}