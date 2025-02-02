package org.example.library_management_system.controller.sub;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.example.library_management_system.dto.custom.PublisherDTO;
import org.example.library_management_system.service.custom.PublisherService;
import org.example.library_management_system.service.custom.impl.PublisherServiceIMPL;
import org.example.library_management_system.util.exceptions.ServiceExeption;
import org.example.library_management_system.util.exceptions.custom.PublisherException;

import java.util.Optional;

public class ManageAuthorAndPublishersController {
    public TextField txtPublisherid;
    public TextField txtPublisherName;
    public TextField txtPublisherLocation;
    public TextField txtPublisherContact;
    public TableView tblPublishers;
    public TableColumn colPublisherId;
    public TableColumn colPublisherName;
    public TableColumn colPublisherContact;
    public TextField txtAuthorId;
    public TextField txtAuthorName;
    public TextField txtAuthorContact;
    public TableView tblAuthors;
    public TableColumn colAuthorId;
    public TableColumn colAuthorName;
    public TableColumn colAuthorContact;

    private PublisherService publisherService = new PublisherServiceIMPL();

    public void btnSavePublisheronAction(ActionEvent actionEvent) {
        PublisherDTO publisherDTO = collectData();
        try {
            boolean isSaved = publisherService.add(publisherDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Saved Success").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        } catch (ServiceExeption e) {
            //if (e instanceof PublisherException) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            //}
        }
    }

    public void btnUpdatePublisherOnAction(ActionEvent actionEvent) {
    }

    public void btnDeletePublisherOnAction(ActionEvent actionEvent) {
    }

    public void btnClearPublisherOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveAuthoronAction(ActionEvent actionEvent) {
    }

    public void btnUpdateAuthorOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteAuthorOnAction(ActionEvent actionEvent) {
    }

    public void btnClearAuthorOnAction(ActionEvent actionEvent) {
    }

    private PublisherDTO collectData(){
        String id = txtPublisherid.getText();
        String name = txtPublisherName.getText();
        String contact = txtPublisherContact.getText();
        String location = txtPublisherLocation.getText();

        int idnum = 0;
        try{
           idnum= Integer.parseInt(id);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(idnum);
        publisherDTO.setName(name);
        publisherDTO.setContact(contact);
        publisherDTO.setLocation(location);

        return publisherDTO;
    }

    public void txtPublisheridOnAction(ActionEvent actionEvent) {
        PublisherDTO publisherDTO = collectData();
        try {
            Optional<PublisherDTO> search = publisherService.search(publisherDTO.getId());
            if (search.isPresent()) {
                setDataFields(search.get());
            }else {
                new Alert(Alert.AlertType.ERROR, "No Publisher found").show();
            }
        } catch (ServiceExeption e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setDataFields(PublisherDTO publisherDTO) {
        txtPublisherid.setText(String.valueOf(publisherDTO.getId()));
        txtPublisherName.setText(publisherDTO.getName());
        txtPublisherContact.setText(publisherDTO.getContact());
        txtPublisherLocation.setText(publisherDTO.getLocation());
    }
}
