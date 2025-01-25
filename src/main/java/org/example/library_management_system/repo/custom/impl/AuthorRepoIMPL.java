package org.example.library_management_system.repo.custom.impl;

import org.example.library_management_system.entity.Author;
import org.example.library_management_system.repo.custom.AuthorRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AuthorRepoIMPL implements AuthorRepo {
    @Override
    public boolean save(Author author) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Author author) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<Author> search(Integer integer) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer integer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Author> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
