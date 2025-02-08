package org.example.library_management_system.service.custom.impl;

import org.example.library_management_system.dto.custom.AuthorDTO;
import org.example.library_management_system.entity.custom.Author;
import org.example.library_management_system.repo.custom.AuthorRepo;
import org.example.library_management_system.service.custom.AuthorService;
import org.example.library_management_system.util.exceptions.ServiceExeption;
import org.example.library_management_system.util.exceptions.custom.AuthorExceptions;
import org.example.library_management_system.util.exceptions.custom.BookException;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthorServiceIMPL implements AuthorService {
    //property injection
    final AuthorRepo authorRepo;
    final ModelMapper mapper;

    //constructor through injection
    public AuthorServiceIMPL(ModelMapper mapper,AuthorRepo authorRepo ) {
        this.authorRepo = authorRepo;
        this.mapper = mapper;
    }

    @Override
    public boolean add(AuthorDTO authorDTO) throws AuthorExceptions {
        Author author = convertToEntity(authorDTO);
        try {
            return authorRepo.save(author)==null;
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException) e).getErrorCode() == 1062) {
                throw new AuthorExceptions("ID already exists-cannot Save ");
            } else if (((SQLException) e).getErrorCode() == 1406) {
                String message = ((SQLException) e).getMessage();
                String[] s = message.split("'");
                throw new AuthorExceptions("Data is To Large For "+s[1]);
            }
            throw new AuthorExceptions("Error",e);
        }

    }

    @Override
    public boolean update(AuthorDTO authorDTO) throws AuthorExceptions {
        Author author = convertToEntity(authorDTO);
        try {
           return   authorRepo.update(author);
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException) e).getErrorCode() == 1406) {
                String message = ((SQLException) e).getMessage();
                String[] s = message.split("'");
                throw new AuthorExceptions("Data is To Large For "+s[1]);
            }
            throw new AuthorExceptions("Error",e);
        }

    }

    @Override
    public boolean delete(Integer integer) throws AuthorExceptions{
        try {
           return authorRepo.delete(integer);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new AuthorExceptions("Error",e);
        }

    }

    @Override
    public Optional<AuthorDTO> search(Integer integer) throws AuthorExceptions {
        try {
            Optional<Author> search = authorRepo.search(integer);
            if (search.isPresent()) {
                Author author = search.get();
                AuthorDTO authorDTO = convertToDto(author);
                return Optional.of(authorDTO);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new AuthorExceptions("Error",e);
        }
        return Optional.empty();
    }

    @Override
    public List<AuthorDTO> getAll() throws AuthorExceptions {
        try {
            List<Author> all = authorRepo.getAll();
            return all.stream().map(this::convertToDto).toList();
            /*List<AuthorDTO> list = new ArrayList<>();
            for (Author author : all) {
                AuthorDTO authorDTO = convertToDto(author);
                list.add(authorDTO);
            }
            return list;*/
        } catch (SQLException | ClassNotFoundException e) {
            throw new AuthorExceptions("Error",e);
        }
    }

    public Author convertToEntity(AuthorDTO authorDTO)  {
        return this.mapper.map(authorDTO, Author.class);
    }

    private AuthorDTO convertToDto(Author author)  {
     return this.mapper.map(author,AuthorDTO.class);
    }
}
