package org.example.service;

import org.example.model.Car;
import org.example.repository.CarRepository;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void addCar(String model) throws SQLException {
        carRepository.createOrUpdate(new Car(model));
    }

    @Override
    public void editModel(Long id, String newModel) throws SQLException {
        Optional<Car> optCar = carRepository.findById(id);
        Car car = optCar.orElseThrow();
        updateCarModel(car, newModel);
    }

    private void updateCarModel(Car car, String newModel) {
        car.setModel(newModel);
        try {
            carRepository.createOrUpdate(car);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCar(String model) throws SQLException {
        return carRepository.deleteByModel(model);
    }

    @Override
    public Set<Car> findByModel(String model) throws SQLException {
        return carRepository.findByModel(model);
    }

}
