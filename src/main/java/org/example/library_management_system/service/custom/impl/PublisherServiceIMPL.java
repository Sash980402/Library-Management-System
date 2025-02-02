package org.example.library_management_system.service.custom.impl;

import org.example.library_management_system.dto.custom.PublisherDTO;
import org.example.library_management_system.entity.custom.Publisher;
import org.example.library_management_system.repo.custom.PublisherRepo;
import org.example.library_management_system.repo.custom.impl.PublisherRepoIMPL;
import org.example.library_management_system.service.custom.PublisherService;
import org.example.library_management_system.util.exceptions.ServiceExeption;
import org.example.library_management_system.util.exceptions.custom.BookException;
import org.example.library_management_system.util.exceptions.custom.PublisherException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PublisherServiceIMPL implements PublisherService {

    private PublisherRepo repo = new PublisherRepoIMPL();

    @Override
    public boolean add(PublisherDTO publisherDTO) throws PublisherException {
        Publisher publisher = convertToEntity(publisherDTO);
        try {
            return repo.save(publisher);
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException) e).getErrorCode() == 1062) {
                throw new PublisherException("ID already exists-cannot Save ");
            } else if (((SQLException) e).getErrorCode() == 1406) {
                String message = ((SQLException) e).getMessage();
                String[] s = message.split("'");
                throw new PublisherException("Data is To Large For "+s[1]);
            }
            throw new PublisherException("Error",e);
        }

    }

    @Override
    public boolean update(PublisherDTO publisherDTO) throws PublisherException {
        Publisher publisher = convertToEntity(publisherDTO);
        try {
            return repo.update(publisher);
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException) e).getErrorCode() == 1406) {
                String message = ((SQLException) e).getMessage();
                String[] s = message.split("'");
                throw new PublisherException("Data is To Large For "+s[1]);
            }
            throw new PublisherException("Error",e);
        }
    }

    @Override
    public boolean delete(Integer integer) throws PublisherException {
        try {
           return repo.delete(integer);
        } catch (SQLException | ClassNotFoundException e) {
            throw new PublisherException("Error");
        }

    }

    @Override
    public Optional<PublisherDTO> search(Integer integer) throws PublisherException {
        try {
            Optional<Publisher> search = repo.search(integer);
            if (search.isPresent()) {
                return Optional.of(convertToDTO(search.get()));
            }
            return Optional.empty();
        } catch (SQLException | ClassNotFoundException e) {
            throw new PublisherException("Error");
        }

    }

    @Override
    public List<PublisherDTO> getAll() throws PublisherException{
        try {
            List<Publisher> all = repo.getAll();
            ArrayList<PublisherDTO> publisherDTOS = new ArrayList<>();
            for (Publisher publisher : all) {
                PublisherDTO publisherDTO = convertToDTO(publisher);
                publisherDTOS.add(publisherDTO);
            }
            return publisherDTOS;
        } catch (SQLException | ClassNotFoundException e) {
            throw new PublisherException("Error",e);
        }

    }

    private PublisherDTO convertToDTO(Publisher publisher){
       PublisherDTO publisherDTO= new PublisherDTO();
       publisherDTO.setId(publisher.getId());
       publisherDTO.setName(publisher.getName());
       publisherDTO.setLocation(publisher.getLocation());
       publisherDTO.setContact(publisher.getContact());
       return publisherDTO;
    }

    private Publisher convertToEntity(PublisherDTO publisherDTO){
        Publisher publisher = new Publisher();
        publisher.setId(publisherDTO.getId());
        publisher.setName(publisherDTO.getName());
        publisher.setLocation(publisherDTO.getLocation());
        publisher.setContact(publisherDTO.getContact());
        return publisher;
    }
}
