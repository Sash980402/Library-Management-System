package org.example.library_management_system.service.custom.impl;

import org.example.library_management_system.dto.custom.CategoryDTO;
import org.example.library_management_system.entity.custom.Category;
import org.example.library_management_system.repo.custom.CategoryRepo;
import org.example.library_management_system.service.custom.CategoryService;
import org.example.library_management_system.util.exceptions.ServiceExeption;
import org.example.library_management_system.util.exceptions.custom.CategoryException;
import org.example.library_management_system.util.exceptions.custom.MemberException;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoryServiceIMPL implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final ModelMapper mapper;
    public CategoryServiceIMPL( ModelMapper mapper, CategoryRepo repo) {
        this.categoryRepo = repo;
        this.mapper = mapper;
    }

    @Override
    public boolean add(CategoryDTO categoryDTO) throws CategoryException {
        Category category = convertToEntity(categoryDTO);
        try {
            return categoryRepo.save(category);

        } catch (SQLException | ClassNotFoundException e) {
            if (e instanceof SQLException) {
                if (((SQLException) e).getErrorCode() == 1062) {
                    throw new CategoryException("ID already exists-cannot Save ");
                } else if (((SQLException) e).getErrorCode() == 1406) {
                    String message = ((SQLException) e).getMessage();
                    String[] s = message.split("'");
                    throw new CategoryException("Data is To Large For "+s[1]);
                }
            }
            e.printStackTrace();
            throw new CategoryException("Error in saving member", e);
        }


    }

    @Override
    public boolean update(CategoryDTO categoryDTO) throws CategoryException {
        Category category = convertToEntity(categoryDTO);
        try {
            return categoryRepo.update(category);
        } catch (SQLException | ClassNotFoundException e) {
            if (e instanceof SQLException) {
                if (((SQLException) e).getErrorCode() == 1406) {
                    String message = ((SQLException) e).getMessage();
                    String[] s = message.split("'");
                    throw new CategoryException("Data is To Large For "+s[1]);

                }
            }
            throw new CategoryException("Error in updating member", e);
        }

    }

    @Override
    public boolean delete(Integer id) throws CategoryException{
        try {
            return categoryRepo.delete(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new CategoryException("Failed to delete member", e);
        }
    }

    @Override
    public Optional<CategoryDTO> search(Integer id) throws CategoryException {
        try {
            Optional<Category> search = categoryRepo.search(id);
            if (search.isPresent()) {
                return Optional.of(convertToDto(search.get()));
            }
            return Optional.empty();
        } catch (SQLException | ClassNotFoundException e) {
           throw new CategoryException("Error", e);
        }
    }

    @Override
    public List<CategoryDTO> getAll() throws CategoryException{
        try {
            return categoryRepo.getAll().stream().map(this::convertToDto).toList();
        } catch (SQLException | ClassNotFoundException e) {
            throw new CategoryException("Error", e);
        }

    }

    private Category convertToEntity(CategoryDTO categoryDTO)  {
        return mapper.map(categoryDTO, Category.class);
    }

    private CategoryDTO convertToDto(Category category)  {
        return mapper.map(category, CategoryDTO.class);
    }
}
