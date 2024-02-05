package org.example.model;

import java.util.Objects;

public class Car {
    private Long id;
    private String model;

    private Owner owner;

    public Car() {}

    public Car(String model) {
        this.model = model;
    }
    public Car(Long id, String model) {
        this.id = id;
        this.model = model;
    }

    public Car(String model, Owner owner) {
        this.model = model;
        this.owner = owner;
    }

    public Car(Long id, String model, Owner owner) {
        this.id = id;
        this.model = model;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(getId(), car.getId()) && Objects.equals(getModel(), car.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
