package src.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import src.model.bl.AccountBl;
import src.model.bl.CustomerBl;
import src.model.bl.TransactionBl;
import src.model.entity.AppData;
import src.model.entity.Customer;
import src.model.entity.Transaction;
import src.view.WindowsManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

//todo: لطفا چک شود
@Log4j
public class CustomerTransactionController implements Initializable {
    @FXML
    private TextField amountField, accountField;

    @FXML
    private Button customerTransferBtn, customerWithdrawalBtn, customerDepositBtn, customerReceiptBtn;

    @FXML
    private TableView<Transaction> customerTable;

    @FXML
    private TableColumn<Customer, Integer> destinationCol;

    @FXML
    private TableColumn<Customer, String> amountCol, balanceCol, typeCol, dateCol;

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
                //todo: واریز وجه
                Transaction transaction = Transaction
                        .builder()
                        .amount(Integer.parseInt(amountField.getText()))
                        .destinationAccount(AccountBl.getAccountBl().findByAccountNumber(Integer.parseInt(accountField.getText())))
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
                //todo: انتقال وجه
                Transaction transaction = Transaction
                        .builder()
                        .amount(Integer.parseInt(amountField.getText()))
                        .sourceAccount(AccountBl.getAccountBl().findByCustomerId(AppData.customer.getId()))
                        .destinationAccount(AccountBl.getAccountBl().findByAccountNumber(Integer.parseInt(accountField.getText())))
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
                //todo: برداشت وجه
                Transaction transaction = Transaction
                        .builder()
                        .amount(Integer.parseInt(amountField.getText()))
                        .sourceAccount(AccountBl.getAccountBl().findByCustomerId(AppData.customer.getId()))
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

        customerReceiptBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(WindowsManager.class.getResource("../view/ReceiptPanel.fxml"))
                );
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error: \n" + e.getMessage());
                alert.show();
                log.error("ReceiptPanel Error : " + e.getMessage());
            }
        });
    }

    private void showDataOnTable(List<Transaction> transactionList) throws Exception {
        ObservableList<Transaction> observableList = FXCollections.observableList(transactionList);
        destinationCol.setCellValueFactory(new PropertyValueFactory<>("account_dst"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("transactionDateTime"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        customerTable.setItems(observableList);
    }

    private void resetForm() throws Exception {
        accountField.clear();
        amountField.clear();
        showDataOnTable(TransactionBl.getTransactionBl().findAll());
    }
}
