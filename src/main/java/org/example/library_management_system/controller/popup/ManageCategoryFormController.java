package org.example.library_management_system.controller.popup;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.library_management_system.dto.custom.CategoryDTO;
import org.example.library_management_system.service.custom.CategoryService;
import org.example.library_management_system.service.util.OtherDependancies;
import org.example.library_management_system.service.util.ServiceFactory;
import org.example.library_management_system.service.util.ServiceType;
import org.example.library_management_system.tm.CategoryTM;
import org.example.library_management_system.util.exceptions.ServiceExeption;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

public class ManageCategoryFormController {
    public TextField txtCategoryid;
    public TextField txtCategoryname;
    public TableView<CategoryTM> tblCategories;
    public TableColumn<CategoryTM,Integer> colCategoryid;
    public TableColumn<CategoryTM,String> colCategoryName;

    CategoryService service = (CategoryService) ServiceFactory.getInstance().getService(ServiceType.CATEGORY_SERVICE);
    ModelMapper mapper = OtherDependancies.getInstance().getMapper();

    public void initialize() {
       setCellValueFactory();
       loadTableData();
    }

    private void setCellValueFactory() {
        colCategoryid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCategoryName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void loadTableData() {
        try {
            List<CategoryTM> list = service.getAll().stream().map(e -> mapper.map(e, CategoryTM.class)).toList();
            tblCategories.setItems(FXCollections.observableArrayList(list));

        } catch (ServiceExeption e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        CategoryDTO categoryDTO = collectData();
        try {
            boolean isadded = service.add(categoryDTO);
            if (isadded) {
                new Alert(Alert.AlertType.INFORMATION, "Category added successfully").show();
                clearFields();
                loadTableData();
            }else {
                new Alert(Alert.AlertType.ERROR, "Category already exist").show();
            }
        } catch (ServiceExeption e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        CategoryDTO categoryDTO = collectData();
        if (categoryDTO.getId()==0){
            new Alert(Alert.AlertType.ERROR, "Invalid Id").show();
            return;
        }
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this category?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.isPresent()){
            if (buttonType.get().equals(ButtonType.YES)){
                try {
                    boolean isDeleted = service.delete(categoryDTO.getId());
                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Category deleted successfully").show();
                        loadTableData();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Category Not Deleted").show();
                    }
                }catch (ServiceExeption e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        CategoryDTO categoryDTO = collectData();
        if (categoryDTO.getId() ==0){
            new Alert(Alert.AlertType.ERROR, "Invalid Id").show();
            return;
        }
        try {
            boolean isUpdated = service.update(categoryDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Category updated successfully").show();
                clearFields();
                loadTableData();
            }else {
                new Alert(Alert.AlertType.ERROR, "Not Updated").show();
            }
        }catch (ServiceExeption e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void clearFields() {
        txtCategoryid.clear();
        txtCategoryname.clear();
    }

    private CategoryDTO collectData(){
        String idtext = txtCategoryid.getText();
        String categoryName = txtCategoryname.getText();
        int id = 0;
        try {
            id = Integer.parseInt(idtext);
        }catch (NumberFormatException e){

        }

        return CategoryDTO.builder().id(id).name(categoryName).build();
    }

    public void txtSearchIdOnAction(ActionEvent actionEvent) {
        CategoryDTO categoryDTO = collectData();
        try {
            Optional<CategoryDTO> search = service.search(categoryDTO.getId());
            if (search.isPresent()) {
                txtCategoryname.setText(search.get().getName());
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
            }
        }catch (ServiceExeption e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
