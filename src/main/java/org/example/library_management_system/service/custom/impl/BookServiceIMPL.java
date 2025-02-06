package org.example.library_management_system.service.custom.impl;

import org.example.library_management_system.dto.custom.BookDTO;

import org.example.library_management_system.entity.custom.Book;
import org.example.library_management_system.repo.custom.BookRepo;
import org.example.library_management_system.repo.custom.impl.BookRepoIMPL;
import org.example.library_management_system.service.custom.BookService;
import org.example.library_management_system.util.exceptions.custom.BookException;
import org.modelmapper.ModelMapper;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceIMPL implements BookService {
    private final ModelMapper mapper ;
    private final BookRepo repo ;

    public BookServiceIMPL(ModelMapper mapper, BookRepo repo) {
        this.mapper = mapper;
        this.repo = repo;
    }


    @Override
    public boolean add(BookDTO bookDTO) throws BookException {
        Book book = this.convertDtoToEntity(bookDTO);
        try {
            return repo.save(book);
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException) e).getErrorCode() == 1062) {
                throw new BookException("ID already exists-cannot Save ");
            } else if (((SQLException) e).getErrorCode() == 1406) {
                String message = ((SQLException) e).getMessage();
                String[] s = message.split("'");
                throw new BookException("Data is To Large For "+s[1]);
            }
            throw new BookException("Error",e);
        }

    }

    @Override
    public boolean update(BookDTO bookDTO) throws BookException {
        Book book = this.convertDtoToEntity(bookDTO);
        try {
          return repo.update(book);
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException) e).getErrorCode() == 1406) {
                String message = ((SQLException) e).getMessage();
                String[] s = message.split("'");
                throw new BookException("Data is To Large For "+s[1]);
            }
            throw new BookException("Error",e);
        }
        
    }

    @Override
    public boolean delete(Integer integer) throws BookException {
        try {
          return   repo.delete(integer);
        } catch (SQLException | ClassNotFoundException e) {
            throw new BookException("Error",e);
        }

    }

    @Override
    public Optional<BookDTO> search(Integer integer) throws BookException {
        try {
            Optional<Book> search = repo.search(integer);
            if (search.isPresent()){
                Book book = search.get();
                BookDTO bookDTO = convertEntityToDto(book);
                return Optional.of(bookDTO);

            }
            return Optional.empty();
        } catch (SQLException | ClassNotFoundException e) {
            throw new BookException("Error",e);
        }

    }

    @Override
    public List<BookDTO> getAll() throws BookException {
        try {
            List<Book> all = repo.getAll();
            List<BookDTO> newList= new ArrayList<>();
            for (Book book : all){
                BookDTO bookDTO = convertEntityToDto(book);
                newList.add(bookDTO);
            }
            return newList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new BookException("Error",e);
        }
    }

    private Book convertDtoToEntity(BookDTO dto){
        return mapper.map(dto, Book.class);

    }


    public BookDTO convertEntityToDto(Book book){
        return mapper.map(book, BookDTO.class);

    }
}
