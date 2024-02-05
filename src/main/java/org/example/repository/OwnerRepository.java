package org.example.repository;

import org.example.model.Owner;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface OwnerRepository extends Repository<Owner, String> {
    Set<Owner> findByName(String name) throws SQLException;
    Optional<Owner> findById(Long id) throws SQLException;
    Boolean deleteById(String id) throws SQLException;

}
