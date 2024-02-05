package org.example.repository;


import org.example.dbconnection.H2DbEmbedded;
import org.example.model.Car;
import org.junit.jupiter.api.BeforeAll;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;


class CarDbRepositoryImplTest {

    static CarRepository carRepository;
    Car car = new Car( "Lada");
    Car carUnit = new Car(2L, "Lada");

    @BeforeAll
    static void initDB() {
        try {
            H2DbEmbedded.initDb();
            carRepository= new CarDbRepositoryImpl(H2DbEmbedded.getConnection());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void createOrUpdate() {
        try {
            assertEquals(car, carRepository.createOrUpdate(car));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void findById() {
        try {
            carRepository.createOrUpdate(car);
            assertEquals(Optional.empty(), carRepository.findById(100L));
            assertEquals(Optional.of(carUnit), carRepository.findById(2L));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void findByModel() {
        try {
            carRepository.createOrUpdate(car);
            assertEquals(Set.of(), carRepository.findByModel("BMW"));
            assertEquals(Set.of(carUnit), carRepository.findByModel("Lada"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void deleteModel() {
        try {
            Car car = new Car( "Toyota");
            carRepository.createOrUpdate(car);
            assertTrue(carRepository.deleteByModel("Toyota"));
            assertFalse(carRepository.deleteByModel("Toyota"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}