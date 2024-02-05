package org.example.repository;

import java.sql.SQLException;

import java.util.Optional;


public interface Repository<T, I>{
    T createOrUpdate(T t) throws SQLException;
    Optional<T> findById(Long id) throws SQLException;
    Boolean deleteByModel(String model) throws SQLException;


}
