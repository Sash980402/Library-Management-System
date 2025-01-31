package org.example.library_management_system.service.custom.impl;

import org.example.library_management_system.dto.custom.PublisherDTO;
import org.example.library_management_system.entity.custom.Publisher;
import org.example.library_management_system.repo.custom.PublisherRepo;
import org.example.library_management_system.repo.custom.impl.PublisherRepoIMPL;
import org.example.library_management_system.service.custom.PublisherService;
import org.example.library_management_system.util.exceptions.ServiceExeption;
import org.example.library_management_system.util.exceptions.custom.PublisherException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PublisherServiceIMPL implements PublisherService {

    private PublisherRepo repo = new PublisherRepoIMPL();

    @Override
    public boolean add(PublisherDTO publisherDTO) throws PublisherException {
        Publisher publisher = convertToEntity(publisherDTO);
        try {
            repo.save(publisher);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean update(PublisherDTO publisherDTO) throws PublisherException {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws PublisherException {
        return false;
    }

    @Override
    public Optional<PublisherDTO> search(Integer integer) throws PublisherException {
        return Optional.empty();
    }

    @Override
    public List<PublisherDTO> getAll() throws PublisherException{
        return List.of();
    }

    private PublisherDTO convertToDTO(Publisher publisher){
       PublisherDTO publisherDTO= new PublisherDTO();
       publisherDTO.setId(publisherDTO.getId());
       publisherDTO.setName(publisherDTO.getName());
       publisherDTO.setLocation(publisherDTO.getLocation());
       publisherDTO.setContact(publisherDTO.getContact());
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
