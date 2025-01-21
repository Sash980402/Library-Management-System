package org.example.library_management_system.controller.sub;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.example.library_management_system.dto.MemberDTO;
import org.example.library_management_system.service.MemberService;
import org.example.library_management_system.util.exceptions.MemberException;

import java.util.Optional;

public class ManageMemberFormController {
    public TextField txtMemberid;
    public TextField txtMemberName;
    public TextField txtMemberAddress;
    public TextField txtMemberEmail;
    public TextField txtMemberContact;
    public TableView tblMember;
    public TableColumn colMemberid;
    public TableColumn colMemberName;
    public TableColumn colMemberAddress;
    public TableColumn colMemberEmail;
    public TableColumn colMemberContact;

    private final MemberService service = new MemberService();


    public void txtMemberidOnAction(ActionEvent actionEvent) {
        Optional<MemberDTO> search = service.search(txtMemberid.getText());
        if (search.isPresent()){
            setDataToFields(search.get());
        }else {
            new Alert(Alert.AlertType.ERROR,"Member Not Found").show();
        }
    }

    public void txtMemberEmailOnAction(ActionEvent actionEvent) {
    }

    public void txtMemberContactOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        MemberDTO memberDTO = collectData();
        boolean isMemberSaved = false;
        String errorMassage = "Unexpected Error";
        try {
            isMemberSaved = service.addMember(memberDTO);
        } catch (MemberException e) {
            errorMassage = e.getMessage();
        }
        if (isMemberSaved){
            new Alert(Alert.AlertType.INFORMATION,"Member saved successfully").show();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,errorMassage).show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        MemberDTO memberDTO = collectData();
        boolean isUpdated = false;
        String errorMassage = "Data is Already Same";
        try {
            isUpdated  = service.update(memberDTO);
        } catch (MemberException e) {
            errorMassage= e.getMessage();
        }
        if (isUpdated){
            new Alert(Alert.AlertType.INFORMATION,"Member updated successfully").show();
            clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR,errorMassage).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String memberId = txtMemberid.getText();
        boolean delete = false;
        String errorMassage = "User Cancelled-Not Deleted";

        Alert areyousure = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete?", ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> buttonType = areyousure.showAndWait();
        if (buttonType.isPresent()){
            ButtonType pressedbutton = buttonType.get();
            if (pressedbutton.equals(ButtonType.YES)){
                try {
                    delete = service.delete(memberId);
                    if (!delete){
                        errorMassage = "User Not Found-Check ID";
                    }
                } catch (MemberException e) {
                    errorMassage = e.getMessage();
                }

            }
        }

        if (delete){
            new Alert(Alert.AlertType.INFORMATION,"Member deleted successfully").show();
        }else{
            new Alert(Alert.AlertType.ERROR,errorMassage).show();
        }

    }



    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public MemberDTO collectData() {
        String id = txtMemberid.getText();
        String name = txtMemberName.getText();
        String address = txtMemberAddress.getText();
        String email = txtMemberEmail.getText();
        String contact = txtMemberContact.getText();
        MemberDTO memberDTO = new MemberDTO(id, name, address, email, contact);
        return memberDTO;
    }

    public void setDataToFields(MemberDTO member){
        txtMemberid.setText(member.getId());
        txtMemberName.setText(member.getName());
        txtMemberAddress.setText(member.getAddress());
        txtMemberEmail.setText(member.getEmail());
        txtMemberContact.setText(member.getMobileNumber());
    }

    public void clearFields(){
        txtMemberid.clear();
        txtMemberName.clear();
        txtMemberAddress.clear();
        txtMemberEmail.clear();
        txtMemberContact.clear();
    }
}
