package src.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import src.model.bl.AccountBl;
import src.model.bl.TransactionBl;
import src.model.entity.AppData;
import src.model.entity.Transaction;
import src.model.entity.enums.TransactionTypes;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class CustomerTransactionController implements Initializable {
    @FXML
    private TextField amountField, accountField;

    @FXML
    private Button customerTransferBtn, customerWithdrawalBtn, customerDepositBtn;

    @FXML
    private TableView<Transaction> customerTable;

    @FXML
    private TableColumn<Transaction, Integer> accountCol;

    @FXML
    private TableColumn<Transaction, String> amountCol, typeCol, dateCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("Entered Customer");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Customer Error\n" + e.getMessage());
            alert.show();
        }

        customerDepositBtn.setOnAction(event -> {
            try {
                Transaction transaction = Transaction
                        .builder()
                        .id(0)
                        .amount(Integer.parseInt(amountField.getText()))
                        .sourceAccount(AccountBl.getAccountBl().findByAccountNumber(AppData.customer.getId()))
                        .destinationAccount(AccountBl.getAccountBl().findByAccountNumber(AppData.customer.getId()))
                        .transactionDateTime(Timestamp.valueOf(LocalDateTime.now()))
                        .transactionType(TransactionTypes.Deposit)
                        .build();
                TransactionBl.getTransactionBl().save(transaction);
//                AccountBl.getAccountBl().edit(amountField);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Done!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        customerTransferBtn.setOnAction(event -> {
            try {
                Transaction transaction = Transaction
                        .builder()
                        .id(0)
                        .amount(Integer.parseInt(amountField.getText()))
                        .sourceAccount(AccountBl.getAccountBl().findByCustomerId(AppData.customer.getId()))
                        .destinationAccount(AccountBl.getAccountBl().findByAccountNumber(Integer.parseInt(accountField.getText())))
                        .transactionDateTime(Timestamp.valueOf(LocalDateTime.now()))
                        .transactionType(TransactionTypes.Transfer)
                        .build();
                TransactionBl.getTransactionBl().save(transaction);
//                AccountBl.getAccountBl().edit(amountField);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Done!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        customerWithdrawalBtn.setOnAction(event -> {
            try {
                Transaction transaction = Transaction
                        .builder()
                        .id(0)
                        .amount(Integer.parseInt(amountField.getText()))
                        .sourceAccount(AccountBl.getAccountBl().findByCustomerId(AppData.customer.getId()))
                        .destinationAccount(AccountBl.getAccountBl().findByCustomerId(AppData.customer.getId()))
                        .transactionDateTime(Timestamp.valueOf(LocalDateTime.now()))
                        .transactionType(TransactionTypes.Withdrawal)
                        .build();
                TransactionBl.getTransactionBl().save(transaction);
//                AccountBl.getAccountBl().edit(amountField);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Done!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });
        customerTable.setOnMouseClicked((event) -> {
            Transaction transaction = customerTable.getSelectionModel().getSelectedItem();
            amountField.setText(String.valueOf(transaction.getAmount()));
            accountField.setText(String.valueOf(transaction.getDestinationAccount()));
        });
    }

    private void showDataOnTable(List<Transaction> transactionList) throws Exception {
        ObservableList<Transaction> observableList = FXCollections.observableList(transactionList);
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("transactionDateTime"));
        accountCol.setCellValueFactory(new PropertyValueFactory<>("destinationAccount"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        customerTable.setItems(observableList);
    }

    private void resetForm() throws Exception {
        accountField.clear();
        amountField.clear();
        showDataOnTable(TransactionBl.getTransactionBl().findAll());
    }
}
