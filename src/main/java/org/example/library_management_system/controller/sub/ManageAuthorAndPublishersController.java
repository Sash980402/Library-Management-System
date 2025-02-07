package org.example.library_management_system.controller.sub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.library_management_system.dto.custom.AuthorDTO;
import org.example.library_management_system.dto.custom.PublisherDTO;
import org.example.library_management_system.service.custom.AuthorService;
import org.example.library_management_system.service.custom.PublisherService;
import org.example.library_management_system.service.util.OtherDependancies;
import org.example.library_management_system.service.util.ServiceFactory;
import org.example.library_management_system.service.util.ServiceType;
import org.example.library_management_system.tm.AuthorTM;
import org.example.library_management_system.tm.PublisherTM;
import org.example.library_management_system.util.exceptions.ServiceExeption;
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
    public TableView<AuthorTM> tblAuthors;
    public TableColumn<AuthorTM,Integer> colAuthorId;
    public TableColumn<AuthorTM,String> colAuthorName;
    public TableColumn<AuthorTM,String> colAuthorContact;

    private final PublisherService publisherService =(PublisherService) ServiceFactory.getInstance().getService(ServiceType.PUBLISHER_SERVICE);
    private final ModelMapper modelMapper = OtherDependancies.getInstance().getMapper();
    private final AuthorService authorService =(AuthorService) ServiceFactory.getInstance().getService(ServiceType.AUTHOR_SERVICE);

    public void initialize() {
        txtPublisherid.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPublisherid.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        visualizeData();
        loadOublisherTabledate();
        loadAuthorTabledate();
    }

    public void btnSavePublisheronAction(ActionEvent actionEvent) {
        PublisherDTO publisherDTO = collectPublisherData();
        try {
            boolean isSaved = publisherService.add(publisherDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Saved Success").show();
                clearPublisherFields();
                loadOublisherTabledate();
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
        PublisherDTO publisherDTO = collectPublisherData();
        try {
            boolean update = publisherService.update(publisherDTO);
            if (update) {
                new Alert(Alert.AlertType.INFORMATION, "Updated Success").show();
                clearPublisherFields();
                loadOublisherTabledate();
            }else {
                new Alert(Alert.AlertType.ERROR, "Not Updated").show();
            }
        } catch (ServiceExeption e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeletePublisherOnAction(ActionEvent actionEvent) {
        PublisherDTO publisherDTO = collectPublisherData();
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
                        loadOublisherTabledate();
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
        AuthorDTO authorDTO = collecAuthortData();
        try {
            boolean isadded = authorService.add(authorDTO);
            if (isadded){
                new Alert(Alert.AlertType.INFORMATION, "Saved Success").show();
                clearAuthorFields();
                loadAuthorTabledate();
            }else {
                new Alert(Alert.AlertType.ERROR, "not saved").show();
            }
        } catch (ServiceExeption e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateAuthorOnAction(ActionEvent actionEvent) {
        AuthorDTO authorDTO = collecAuthortData();
        if (authorDTO.getId() == 0){
            new Alert(Alert.AlertType.ERROR, "Invalid Author ID- Please Enter valid ID").show();
            return;
        }
        try {
            boolean isupdated = authorService.update(authorDTO);
            if (isupdated){
                new Alert(Alert.AlertType.INFORMATION, "Updated Success").show();
                clearAuthorFields();
                loadAuthorTabledate();
            }else {
                new Alert(Alert.AlertType.ERROR, "Not Updated").show();
            }
        } catch (ServiceExeption e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnDeleteAuthorOnAction(ActionEvent actionEvent) {
        AuthorDTO authorDTO = collecAuthortData();
        if (authorDTO.getId() == 0){
            new Alert(Alert.AlertType.ERROR, "Invalid Author ID- Please Enter valid ID").show();
            return;
        }
        Optional<ButtonType> pressedButton = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this author?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (pressedButton.isPresent()){
            ButtonType buttonType = pressedButton.get();
            if (buttonType.equals(ButtonType.YES)){
                try {
                    boolean isDeleted = authorService.delete(authorDTO.getId());
                    if (isDeleted){
                        new Alert(Alert.AlertType.INFORMATION, "Deleted Success").show();
                        clearAuthorFields();
                        loadAuthorTabledate();

                    }else{
                        new Alert(Alert.AlertType.ERROR, "Not Deleted").show();
                    }
                } catch (ServiceExeption e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }
    }

    public void btnClearAuthorOnAction(ActionEvent actionEvent) {
        clearAuthorFields();
    }

    public void clearAuthorFields() {
        txtAuthorId.clear();
        txtAuthorName.clear();
        txtAuthorContact.clear();
    }

    private PublisherDTO collectPublisherData(){
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

    private AuthorDTO collecAuthortData(){
        String authorId = txtAuthorId.getText();
        String authorName = txtAuthorName.getText();
        String authorContact = txtAuthorContact.getText();
        int id=0;
        try {
            id = Integer.parseInt(authorId);
        }catch (NumberFormatException e){

        }
        return AuthorDTO.builder().id(id).name(authorName).contact(authorContact).build();
    }

    public void txtPublisheridOnAction(ActionEvent actionEvent) {
        PublisherDTO publisherDTO = collectPublisherData();
        try {
            Optional<PublisherDTO> search = publisherService.search(publisherDTO.getId());
            if (search.isPresent()) {
                setPublisherDatatoFields(search.get());
            }else {
                new Alert(Alert.AlertType.ERROR, "No Publisher found").show();
            }
        } catch (ServiceExeption e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setPublisherDatatoFields(PublisherDTO publisherDTO) {
        txtPublisherid.setText(String.valueOf(publisherDTO.getId()));
        txtPublisherName.setText(publisherDTO.getName());
        txtPublisherContact.setText(publisherDTO.getContact());
        txtPublisherLocation.setText(publisherDTO.getLocation());
    }

    private void setAuthorDataToFields(AuthorDTO authorDTO) {
        txtAuthorId.setText(String.valueOf(authorDTO.getId()));
        txtAuthorName.setText(authorDTO.getName());
        txtAuthorContact.setText(authorDTO.getContact());
    }

    public void loadOublisherTabledate(){
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

    private void loadAuthorTabledate(){
        try {
           tblAuthors.setItems( FXCollections.observableArrayList( authorService.getAll().stream().map(this::convertAuthorToTM).toList()));
        } catch (ServiceExeption e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void visualizeData(){
        colPublisherId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPublisherName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPublisherContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        colAuthorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAuthorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAuthorContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private PublisherTM convertDtoToTM(PublisherDTO obj) {
          return modelMapper.map(obj, PublisherTM.class);
    }

    public void txtAuthorIdOnAction(ActionEvent actionEvent) {
        AuthorDTO authorDTO = collecAuthortData();
        if (authorDTO.getId() == 0){
            new Alert(Alert.AlertType.ERROR, "Invalid Author ID- Please Enter valid ID").show();
            return;
        }
        try {
            Optional<AuthorDTO> search = authorService.search(authorDTO.getId());
            if (search.isPresent()) {
                AuthorDTO authorDTO1 = search.get();
                setAuthorDataToFields(authorDTO1);
            }else{
                new Alert(Alert.AlertType.ERROR, "No Author found or Invalid ID").show();
            }
        }catch (ServiceExeption e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private AuthorTM convertAuthorToTM(AuthorDTO obj) {
        return modelMapper.map(obj, AuthorTM.class);
    }

    public void tblAuthorOnMouseClick(MouseEvent mouseEvent) {
        AuthorTM selectedAuthor = tblAuthors.getSelectionModel().getSelectedItem();
        if (selectedAuthor != null) {
            txtAuthorId.setText(String.valueOf(selectedAuthor.getId()));
            txtAuthorIdOnAction(null);
        }

    }

    public void tblPublisherOnMouseClick(MouseEvent mouseEvent) {
        PublisherTM selectedPublisher = tblPublishers.getSelectionModel().getSelectedItem();
        if (selectedPublisher != null) {
            txtPublisherid.setText(String.valueOf(selectedPublisher.getId()));
            txtPublisheridOnAction(null);
        }
    }
}
