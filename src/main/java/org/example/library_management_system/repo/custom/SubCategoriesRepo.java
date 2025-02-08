package org.example.library_management_system.repo.custom;

import org.example.library_management_system.entity.custom.BookAuthor;
import org.example.library_management_system.entity.custom.SubCategories;
import org.example.library_management_system.repo.CrudRepository;

import java.sql.SQLException;
import java.util.List;

public interface SubCategoriesRepo extends CrudRepository<SubCategories, Integer> {
    public boolean saveList(List<SubCategories> list) throws SQLException, ClassNotFoundException;
}
