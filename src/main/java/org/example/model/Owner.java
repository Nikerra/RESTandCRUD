package org.example.model;

import java.util.Objects;
import java.util.Set;

public class Owner {

    private  long id;
    private String name;
    private String lastName;

    Set<Car> carSet;
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Owner() {}

    public Owner(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
    public Owner(String name, String lastName, Set<Car> carSet) {
        this.name = name;
        this.lastName = lastName;
        this.carSet = carSet;
    }
    public Set<Car> getCarSet() {
        return carSet;
    }

    public void setCarSet(Set<Car> carSet) {
        this.carSet = carSet;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner owner)) return false;
        return id == owner.id && Objects.equals(name, owner.name) && Objects.equals(lastName, owner.lastName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", carSet=" + carSet +
                '}';
    }
}
