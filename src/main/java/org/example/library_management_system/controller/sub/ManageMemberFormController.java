package org.example.library_management_system.controller.sub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.library_management_system.dto.MemberDTO;
import org.example.library_management_system.entity.Member;
import org.example.library_management_system.service.MemberService;
import org.example.library_management_system.tm.MemberTm;
import org.example.library_management_system.util.exceptions.MemberException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageMemberFormController {
    public TextField txtMemberid;
    public TextField txtMemberName;
    public TextField txtMemberAddress;
    public TextField txtMemberEmail;
    public TextField txtMemberContact;
    public TableView<MemberTm> tblMember;
    public TableColumn<MemberTm,String> colMemberid;
    public TableColumn<MemberTm,String> colMemberName;
    public TableColumn<MemberTm,String> colMemberAddress;
    public TableColumn<MemberTm,String> colMemberEmail;
    public TableColumn<MemberTm,String> colMemberContact;

    private final MemberService service = new MemberService();

    public void initialize(){
        loadTableData();
        visualizeTable();
    }

    private void visualizeTable() {
        colMemberid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMemberAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMemberEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMemberContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }


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

    public void loadTableData(){
        try {
            List<MemberTm> list= new ArrayList<>();
            List<MemberDTO> all = service.getAll();
            for (MemberDTO memberDTO : all) {
                MemberTm memberTm = convertMemberDtoToTm(memberDTO);
                list.add(memberTm);
            }
            ObservableList<MemberTm> memberTms = FXCollections.observableArrayList(list);
            tblMember.setItems(memberTms);
        } catch (MemberException e) {
            e.printStackTrace();
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

    private MemberTm convertMemberDtoToTm(MemberDTO memberDTO) {
       MemberTm memberTm = new MemberTm();
       memberTm.setId(memberDTO.getId());
       memberTm.setName(memberDTO.getName());
       memberTm.setAddress(memberDTO.getAddress());
       memberTm.setEmail(memberDTO.getEmail());
       memberTm.setContact(memberDTO.getMobileNumber());
       return memberTm;
    }
}
