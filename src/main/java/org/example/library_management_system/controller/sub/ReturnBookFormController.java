package org.example.library_management_system.controller.sub;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class ReturnBookFormController {
    public TableView tblNotReturnbookRecords;
    public TableColumn colRecordId;
    public TableColumn colBookid;
    public TableColumn colBookName;
    public TableColumn colMemberId;
    public TableColumn colMemberName;
    public TableColumn colReturnDate;
    public TextField txtSearchKeyword;
    public RadioButton rbBookId;
    public RadioButton rbMemberId;
    public RadioButton rbMemberMobileNumber;
    public Label lblLateDateCount;
    public TextField txtFineForOneDay;
    public Label lblFine;
    public TextField txtPayment;
    public Label lblBalance;

    public void btnMarkAsReturnedOnAction(ActionEvent actionEvent) {
    }
}
