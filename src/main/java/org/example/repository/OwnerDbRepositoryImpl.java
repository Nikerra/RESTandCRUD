package org.example.repository;

import org.example.model.Car;
import org.example.model.Owner;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class OwnerDbRepositoryImpl implements OwnerRepository{
    @Override
    public Set<Owner> findByName(String name) throws SQLException {
        return null;
    }

    @Override
    public Optional<Owner> findById(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Boolean deleteByModel(String model) throws SQLException {
        return null;
    }

    @Override
    public Set<Owner> findAll() throws SQLException {
        return null;
    }

    @Override
    public Boolean deleteById(String id) throws SQLException {
        return null;
    }

    @Override
    public Boolean deleteAll() throws SQLException {
        return null;
    }

    @Override
    public Owner createOrUpdate(Owner owner) throws SQLException {
        return null;
    }

    @Override
    public Set<Owner> createAll(Collection<Owner> cars) throws SQLException {
        return null;
    }
}
