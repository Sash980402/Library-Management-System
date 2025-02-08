package org.example.library_management_system.repo.custom;

import org.example.library_management_system.entity.custom.BookAuthor;
import org.example.library_management_system.repo.CrudRepository;

import java.sql.SQLException;
import java.util.List;

public interface BookAuthorRepo extends CrudRepository<BookAuthor, Integer> {
    public boolean saveList(List<BookAuthor> list) throws SQLException, ClassNotFoundException;
}
