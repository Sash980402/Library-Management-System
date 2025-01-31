package org.example.library_management_system.service;

import org.example.library_management_system.dto.SuperDTO;
import org.example.library_management_system.entity.SuperEntity;
import org.example.library_management_system.util.exceptions.ServiceExeption;

import java.util.List;
import java.util.Optional;

public interface CrudService <T extends SuperDTO,ID> {
    boolean add(T t) throws ServiceExeption;

    boolean update(T t) throws ServiceExeption;

    boolean delete(ID id) throws ServiceExeption;

    Optional<T> search(ID id) throws ServiceExeption;

    List<T> getAll()  throws ServiceExeption;
}
