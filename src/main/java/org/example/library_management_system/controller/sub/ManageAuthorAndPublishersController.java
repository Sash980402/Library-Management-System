package org.example.library_management_system.controller.sub;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.library_management_system.dto.custom.PublisherDTO;
import org.example.library_management_system.repo.custom.PublisherRepo;
import org.example.library_management_system.repo.util.RepoFactory;
import org.example.library_management_system.repo.util.RepoTypes;
import org.example.library_management_system.service.custom.PublisherService;
import org.example.library_management_system.service.custom.impl.PublisherServiceIMPL;
import org.example.library_management_system.service.util.ServiceFactory;
import org.example.library_management_system.service.util.ServiceType;
import org.example.library_management_system.tm.PublisherTM;
import org.example.library_management_system.util.exceptions.ServiceExeption;
import org.example.library_management_system.util.exceptions.custom.PublisherException;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageAuthorAndPublishersController {
    public TextField txtPublisherid;
    public TextField txtPublisherName;
    public TextField txtPublisherLocation;
    public TextField txtPublisherContact;
    public TableView<PublisherTM> tblPublishers;
    public TableColumn<PublisherTM,Integer> colPublisherId;
    public TableColumn<PublisherTM,String> colPublisherName;
    public TableColumn<PublisherTM,String> colPublisherContact;
    public TextField txtAuthorId;
    public TextField txtAuthorName;
    public TextField txtAuthorContact;
    public TableView tblAuthors;
    public TableColumn colAuthorId;
    public TableColumn colAuthorName;
    public TableColumn colAuthorContact;

    private final PublisherService publisherService =(PublisherService) ServiceFactory.getInstance().getService(ServiceType.PUBLISHER_SERVICE);
    private final ModelMapper modelMapper = new ModelMapper();

    public void initialize() {
        txtPublisherid.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPublisherid.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        visualizeData();
        loadTabledate();
    }

    public void btnSavePublisheronAction(ActionEvent actionEvent) {
        PublisherDTO publisherDTO = collectData();
        try {
            boolean isSaved = publisherService.add(publisherDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Saved Success").show();
                clearPublisherFields();
                loadTabledate();
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
        PublisherDTO publisherDTO = collectData();
        try {
            boolean update = publisherService.update(publisherDTO);
            if (update) {
                new Alert(Alert.AlertType.INFORMATION, "Updated Success").show();
                clearPublisherFields();
                loadTabledate();
            }else {
                new Alert(Alert.AlertType.ERROR, "Not Updated").show();
            }
        } catch (ServiceExeption e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeletePublisherOnAction(ActionEvent actionEvent) {
        PublisherDTO publisherDTO = collectData();
        if (publisherDTO.getId() == 0){
            new Alert(Alert.AlertType.ERROR, "Invalid Publisher ID- Please Enter valid ID").show();
            return;
        }

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this publisher?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.isPresent()){
            if (buttonType.get().equals(ButtonType.YES)){
                try {
                    boolean delete = publisherService.delete(publisherDTO.getId());
                    if (delete) {
                        new Alert(Alert.AlertType.INFORMATION, "Deleted Success").show();
                        loadTabledate();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Not Delete").show();
                    }
                } catch (ServiceExeption e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }


    }

    private void clearPublisherFields() {
        txtPublisherid.clear();
        txtPublisherName.clear();
        txtPublisherLocation.clear();
        txtPublisherContact.clear();
    }

    public void btnClearPublisherOnAction(ActionEvent actionEvent) {
        clearPublisherFields();
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
           //e.printStackTrace();
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

    public void loadTabledate(){
        try {
            List<PublisherDTO> all = publisherService.getAll();
            List<PublisherTM> list = new ArrayList<>();
            for (PublisherDTO publisherDTO : all) {
                list.add(convertDtoToTM(publisherDTO));
            }
            tblPublishers.setItems(FXCollections.observableArrayList(list));
        } catch (ServiceExeption e) {
            e.printStackTrace();
        }
    }

    private void visualizeData(){
        colPublisherId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPublisherName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPublisherContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private PublisherTM convertDtoToTM(PublisherDTO obj) {
          return modelMapper.map(obj, PublisherTM.class);
    }
}
