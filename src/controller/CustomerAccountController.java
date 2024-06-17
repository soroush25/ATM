package src.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import src.model.bl.AccountBl;
import src.model.bl.CustomerBl;
import src.model.entity.Account;
import src.model.entity.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class CustomerAccountController implements Initializable {
    @FXML
    private TableView<Account> customerTable;

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
    }

    private void showDataOnTable(List<Account> accountList) throws Exception {
        ObservableList<Account> observableList = FXCollections.observableList(accountList);
        numberCol.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        customerTable.setItems(observableList);
    }

    private void resetForm() throws Exception {
        showDataOnTable(AccountBl.getAccountBl().findAll());
    }
}
