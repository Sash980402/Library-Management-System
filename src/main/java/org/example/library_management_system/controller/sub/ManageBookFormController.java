package org.example.library_management_system.controller.sub;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.library_management_system.repo.custom.BookRepo;
import org.example.library_management_system.repo.util.RepoFactory;
import org.example.library_management_system.repo.util.RepoTypes;
import org.example.library_management_system.service.custom.BookService;
import org.example.library_management_system.service.custom.impl.BookServiceIMPL;
import org.example.library_management_system.service.util.OtherDependancies;
import org.example.library_management_system.service.util.ServiceFactory;
import org.example.library_management_system.service.util.ServiceType;
import org.modelmapper.ModelMapper;

public class ManageBookFormController {
    public TextField txtBookid;
    public TextField txtBookName;
    public TextField txtBookisbn;
    public TextField txtPrice;
    public ComboBox cmbPublisher;
    public ComboBox cmbMainCategory;
    public TableView tblSubCategories;
    public TableColumn colCategoryName;
    public TableColumn colOption;
    public TableColumn colBookid;
    public TableColumn colBookName;
    public TableColumn colBookisbn;
    public TableColumn colBookPrice;
    public TableView tblBook;
    public TableView tblAuthors;
    public TableColumn colAuthorName;
    public TableColumn colAuthorOption;


    private final BookService service=(BookService) ServiceFactory.getInstance().getService(ServiceType.BOOK_SERVICE);

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void bookidOnAction(ActionEvent actionEvent) {
    }
}
