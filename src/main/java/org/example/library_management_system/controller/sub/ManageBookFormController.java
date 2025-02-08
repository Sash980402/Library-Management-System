package org.example.library_management_system.controller.sub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.example.library_management_system.cm.CategoryCM;
import org.example.library_management_system.cm.PublisherCM;
import org.example.library_management_system.controller.popup.ManageCategoryFormController;
import org.example.library_management_system.dto.custom.BookDTO;
import org.example.library_management_system.repo.custom.BookRepo;
import org.example.library_management_system.repo.util.RepoFactory;
import org.example.library_management_system.repo.util.RepoTypes;
import org.example.library_management_system.service.custom.AuthorService;
import org.example.library_management_system.service.custom.BookService;
import org.example.library_management_system.service.custom.CategoryService;
import org.example.library_management_system.service.custom.PublisherService;
import org.example.library_management_system.service.custom.impl.BookServiceIMPL;
import org.example.library_management_system.service.util.OtherDependancies;
import org.example.library_management_system.service.util.ServiceFactory;
import org.example.library_management_system.service.util.ServiceType;
import org.example.library_management_system.tm.AuthorTMWithCheckBox;
import org.example.library_management_system.tm.CategoryTMWithBox;
import org.example.library_management_system.util.exceptions.ServiceExeption;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManageBookFormController {
    public TextField txtBookid;
    public TextField txtBookName;
    public TextField txtBookisbn;
    public TextField txtPrice;
    public ComboBox<PublisherCM> cmbPublisher;
    public ComboBox<CategoryCM> cmbMainCategory;

    public TableView<CategoryTMWithBox> tblSubCategories;
    public TableColumn<CategoryTMWithBox, String> colCategoryName;
    public TableColumn<CategoryTMWithBox, CheckBox> colOption;

    public TableColumn colBookid;
    public TableColumn colBookName;
    public TableColumn colBookisbn;
    public TableColumn colBookPrice;
    public TableView tblBook;

    public TableView<AuthorTMWithCheckBox> tblAuthors;
    public TableColumn<AuthorTMWithCheckBox,String> colAuthorName;
    public TableColumn<AuthorTMWithCheckBox, CheckBox> colAuthorOption;

    private final PublisherService publisherService = (PublisherService) ServiceFactory.getInstance().getService(ServiceType.PUBLISHER_SERVICE);
    private final CategoryService categoryService = (CategoryService) ServiceFactory.getInstance().getService(ServiceType.CATEGORY_SERVICE);
    private final AuthorService authorService = (AuthorService) ServiceFactory.getInstance().getService(ServiceType.AUTHOR_SERVICE);
    private final BookService bookservice=(BookService) ServiceFactory.getInstance().getService(ServiceType.BOOK_SERVICE);

    public void initialize() {
        addConverterToComboBox();
        setCellValueFactory();
        try {
            List<PublisherCM> publishers = publisherService.getAll().stream().map(e -> PublisherCM.builder().id(e.getId()).name(e.getName()).build()).toList();
            cmbPublisher.setItems(FXCollections.observableArrayList(publishers));

            loadCategoryData();
            loadAuthorData();
        } catch (ServiceExeption e) {

        }
    }

    public void loadCategoryData() throws ServiceExeption {
        List<CategoryCM> categories = categoryService.getAll().stream().map(e -> CategoryCM.builder().id(e.getId()).name(e.getName()).build()).toList();
        cmbMainCategory.setItems(FXCollections.observableArrayList(categories));

        List<CategoryTMWithBox> list = categories.stream().map(e -> CategoryTMWithBox.builder().id(e.getId()).name(e.getName()).checkBox(new CheckBox()).build()).toList();
        tblSubCategories.setItems(FXCollections.observableArrayList(list));
    }

    private void loadAuthorData() throws ServiceExeption {
        List<AuthorTMWithCheckBox> list = authorService.getAll().stream().map(e->AuthorTMWithCheckBox.builder().id(e.getId()).name(e.getName()).checkBox(new CheckBox()).build()).toList();
        tblAuthors.setItems(FXCollections.observableArrayList(list));
    }

    public void setCellValueFactory() {
        colCategoryName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("checkBox"));

        colAuthorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAuthorOption.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
    }

    public void addConverterToComboBox(){
        cmbPublisher.setConverter(new StringConverter<PublisherCM>() {
            @Override
            public String toString(PublisherCM publisherCM) {
                return publisherCM.getName();
            }

            @Override
            public PublisherCM fromString(String s) {
                return null;
            }
        });

        cmbMainCategory.setConverter(new StringConverter<CategoryCM>() {
            @Override
            public String toString(CategoryCM categoryCM) {
                return categoryCM.getName();
            }

            @Override
            public CategoryCM fromString(String s) {
                return null;
            }
        });
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        BookDTO bookDTO = collectData();
        try {
            bookservice.add(bookDTO);
        } catch (ServiceExeption e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void bookidOnAction(ActionEvent actionEvent) {
    }

    public void btnManageCategoryOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("/view/popup/manage_category_form.fxml"));
            Parent load = loader.load();
            ManageCategoryFormController controller = loader.getController();
            controller.setBaseController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(load));

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(txtBookid.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BookDTO collectData(){
        int bookID = 0;
        String bookName = txtBookName.getText();
        String isbnNumber = txtBookisbn.getText();
        double price = 0;
        int publisherId=0;
        int mainCategoryId=0;
        int count=0;

        try{
            bookID = Integer.parseInt(txtBookid.getText());
        }catch (NumberFormatException e){}
        try{
            price = Double.parseDouble(txtPrice.getText());
        }catch (NumberFormatException e){
            new  Alert(Alert.AlertType.ERROR,"Enter Valid Price");
            return  null;
        }
        try {
           publisherId  = cmbPublisher.getSelectionModel().getSelectedItem().getId();
           count++;
           mainCategoryId = cmbMainCategory.getSelectionModel().getSelectedItem().getId();
        }catch (NumberFormatException e){
           String er = count== 0 ? "Publisher" : "Category";
           new Alert(Alert.AlertType.ERROR,"Selecrt"+er).showAndWait();
        }

        /*ArrayList<Integer> authorIds = new ArrayList<>();
        ObservableList<AuthorTMWithCheckBox> items = tblAuthors.getItems();
        for (AuthorTMWithCheckBox item : items) {
            if (item.getCheckBox().isSelected()) {
                authorIds.add(item.getId());
            }
        }*/

        List<Integer> authorIds = tblAuthors.getItems().stream().filter(e -> e.getCheckBox().isSelected()).map(e -> e.getId()).toList();
        List<Integer> subCategoryIds = tblSubCategories.getItems().stream().filter(e -> e.getCheckBox().isSelected()).map(e -> e.getId()).toList();

        return BookDTO.builder().id(bookID).name(bookName).isbn(isbnNumber).price(price).publisherId(publisherId).mainCategoryId(mainCategoryId).authors(authorIds).subCategoryIds(subCategoryIds).build();

    }
}
