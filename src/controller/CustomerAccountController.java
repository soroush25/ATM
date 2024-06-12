package src.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import src.model.bl.CustomerBl;
import src.model.entity.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class CustomerAccountController implements Initializable {
    @FXML
    private Button customerBalanceBtn, customerReportBtn;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> numberCol, balanceCol, typeCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("Entered CustomerAccount");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Customer Error\n" + e.getMessage());
            alert.show();
        }

        customerBalanceBtn.setOnAction(event -> {
            try {
                // todo: نشان دادن موجودی
                balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        customerReportBtn.setOnAction(event -> {
            try {
                //todo: نشان دادن تراکنش های اخیر در جدول
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });
    }

    private void showDataOnTable(List<Customer> customerList) throws Exception {
        ObservableList<Customer> observableList = FXCollections.observableList(customerList);
        numberCol.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        customerTable.setItems(observableList);
    }

    private void resetForm() throws Exception {
        showDataOnTable(CustomerBl.getCustomerBl().findAll());
    }
}
