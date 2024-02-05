package org.example.repository;

import org.example.model.Owner;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface OwnerRepository extends Repository<Owner, String> {
    Set<Owner> findByName(String name) throws SQLException;
    Optional<Owner> findById(Long id) throws SQLException;
    Set<Owner> findAll() throws SQLException;
    Boolean deleteById(String id) throws SQLException;
    Boolean deleteAll() throws SQLException;
    Set<Owner> createAll(Collection<Owner> cars) throws SQLException;
}
