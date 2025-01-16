package org.example.library_management_system.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginFormController {

    public TextField txtUserName;
    public TextField txtPassword;
    public AnchorPane mainsubAnchorPane;
    public AnchorPane subAnchorPane1;
    public AnchorPane subAnchorPane2;
    public PasswordField pwfPassword;

    public void btnViewAndHideOnAction(ActionEvent actionEvent) {
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
    }

    public void btnCreateAnAccountOnAction(ActionEvent actionEvent) {
        subAnchorPane2.getChildren().clear();

        try {
            Node load = FXMLLoader.load(getClass().getResource("/view/register_form_sub.fxml"));
            ObservableList<Node> children = subAnchorPane2.getChildren();
            children.add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to load the form");
            e.printStackTrace();
        }

    }
}
