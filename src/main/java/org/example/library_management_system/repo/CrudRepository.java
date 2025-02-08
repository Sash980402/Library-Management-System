package org.example.library_management_system.repo;

import org.example.library_management_system.entity.SuperEntity;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository <T extends SuperEntity,ID extends Serializable>{
 T save(T t) throws SQLException, ClassNotFoundException;

 boolean update(T t) throws SQLException, ClassNotFoundException;

 Optional<T> search(ID id) throws SQLException, ClassNotFoundException;

 boolean delete(ID id) throws SQLException, ClassNotFoundException;

 List<T> getAll() throws SQLException, ClassNotFoundException;
}
