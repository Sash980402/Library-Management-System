package org.example.library_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainDashboardFormController {
    public AnchorPane mainAnchoePane;
    public AnchorPane mainbuttonpane;
    public AnchorPane MainPane;

    public void btnManageMembersOnAction(ActionEvent actionEvent) {
        loadUI("manage_member_form.fxml");
    }

    public void btnManageBooksOnAction(ActionEvent actionEvent) {
       loadUI("manage_book_form.fxml");
    }

    public void btnManageAuthorAndPublisherOnAction(ActionEvent actionEvent) {
        loadUI("manage_autors_and_publishers_form.fxml");
    }

    public void btnBorrowBookRecordOnAction(ActionEvent actionEvent) {
        loadUI("borrow_book_form.fxml");
    }

    public void btnReturnBookRecordOnAction(ActionEvent actionEvent) {
        loadUI("return_book_form.fxml");
    }

    public  void  loadUI(String uiName){
        MainPane.getChildren().clear();

        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/sub/"+uiName));
            MainPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to load the form").show();
            e.printStackTrace();
        }
    }
}
