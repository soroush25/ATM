package src.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import src.model.bl.AccountBl;
import src.model.bl.CustomerBl;
import src.model.bl.TransactionBl;
import src.model.entity.Customer;
import src.model.entity.Transaction;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class CustomerTransactionController implements Initializable {
    @FXML
    private TextField amountField, accountField;

    @FXML
    private Button exit, customerTransfer, customerBalance, customerWithdrawal, customerReport, customerDeposit;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, Integer> customerTableDst;

    @FXML
    private TableColumn<Customer, String> customerTableAmount, customerTableBalance, customerTableType, customerTableDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("Entered Customer");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Customer Error\n" + e.getMessage());
            alert.show();
        }

        customerBalance.setOnAction(event -> {
            try {
                // todo: نشان دادن موجودی
                customerTableBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        customerReport.setOnAction(event -> {
            try {
                //todo: نشان دادن تراکنش های اخیر در جدول
                showDataOnTable((List<Customer>) customerReport);
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        customerDeposit.setOnAction(event -> {
            try {
                //todo: واریز وجه
                Transaction transaction = new Transaction()
                        .builder()
                        .amount(Integer.parseInt(amountField.getText()))
                        .destinationAccount(accountField.getText())
                        .build();
                TransactionBl.getTransactionBl().save(transaction);
                AccountBl.getAccountBl().edit(amountField);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Done!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        customerTransfer.setOnAction(event -> {
            try {
                //todo: انتقال وجه
                Transaction transaction = new Transaction()
                        .builder()
                        .amount(Integer.parseInt(amountField.getText()))
                        .destinationAccount(accountField.getText())
                        .build();
                TransactionBl.getTransactionBl().save(transaction);
                AccountBl.getAccountBl().edit(amountField);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Done!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });
        customerWithdrawal.setOnAction(event -> {
            try {
                //todo: برداشت وجه
                Transaction transaction = new Transaction()
                        .builder()
                        .amount(Integer.parseInt(amountField.getText()))
                        .sourceAccount(accountField.getText())
                        .build();
                TransactionBl.getTransactionBl().save(transaction);
                AccountBl.getAccountBl().edit(amountField);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Done!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        exit.setOnAction((event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Quit?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
            log.info("Quited");
        }));

        customerTable.setOnMouseClicked((event) -> {
            Customer customer = customerTable.getSelectionModel().getSelectedItem();
            accountField.setText(String.valueOf(customer.getTransaction().getDestinationAccount()));
            amountField.setText(String.valueOf(customer.getTransaction().getAmount()));
        });
    }

    private void showDataOnTable(List<Customer> customerList) throws Exception {
        ObservableList<Customer> observableList = FXCollections.observableList(customerList);
        customerTableDst.setCellValueFactory(new PropertyValueFactory<>("account_dst"));
        customerTableAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        customerTableDate.setCellValueFactory(new PropertyValueFactory<>("transactionDateTime"));
        customerTableBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        customerTableType.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        customerTable.setItems(observableList);
    }

    private void resetForm() throws Exception {
        accountField.clear();
        amountField.clear();
        showDataOnTable(CustomerBl.getCustomerBl().findAll());
    }
}
