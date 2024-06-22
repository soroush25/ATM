package src.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import src.model.bl.AdminBl;
import src.model.bl.TransactionBl;
import src.model.entity.Admin;
import src.model.entity.Transaction;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class AdminTransactionController implements Initializable {
    @FXML
    private TableView<Transaction> adminTransactionTbl;

    @FXML
    private TableColumn<Admin, Integer> idTransactionCol;

    @FXML
    private TableColumn<Admin, String> amountTransactionCol, sourceTransactionCol, destinationTransactionCol, timeTransactionCol, typeTransactionCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("Entered AdminTransaction");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "AdminTransaction Error\n" + e.getMessage());
            alert.show();
        }
    }

    private void showDataOnTable(List<Transaction> transactionList) throws Exception {
        ObservableList<Transaction> observableList = FXCollections.observableList(transactionList);
        idTransactionCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        amountTransactionCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        timeTransactionCol.setCellValueFactory(new PropertyValueFactory<>("transactionDateTime"));
        sourceTransactionCol.setCellValueFactory(new PropertyValueFactory<>("sourceAccount"));
        destinationTransactionCol.setCellValueFactory(new PropertyValueFactory<>("destinationAccount"));
        typeTransactionCol.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        adminTransactionTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        showDataOnTable(TransactionBl.getTransactionBl().findAll());
    }
}
