package org.example.library_management_system.repo.custom.impl;

import org.example.library_management_system.entity.Book;
import org.example.library_management_system.repo.custom.BookRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookRepoIMPL implements BookRepo {
    @Override
    public boolean save(Book book) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Book book) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Optional<Book> search(Integer integer) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer integer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Book> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
