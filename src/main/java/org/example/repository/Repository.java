package org.example.repository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface Repository<T, I>{
    T createOrUpdate(T t) throws SQLException;

    Set<T> createAll(Collection<T> tCollection) throws SQLException;
    Set<T> findAll() throws SQLException;

    Optional<T> findById(Long id) throws SQLException;
    Boolean deleteByModel(String model) throws SQLException;

    Boolean deleteAll() throws SQLException;
}
