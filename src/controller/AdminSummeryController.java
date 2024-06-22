package src.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.log4j.Log4j;
import src.model.bl.AccountBl;
import src.model.bl.TransactionBl;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
public class AdminSummeryController implements Initializable {
    @FXML
    private TextField adminBalanceField, adminTransactionsField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("Entered AdminSummery");
        try {
            resetForm();
            adminBalanceField.setText(String.valueOf(AccountBl.getAccountBl().balanceSum()));
            adminTransactionsField.setText(String.valueOf(TransactionBl.getTransactionBl().transactionSum()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "AdminSummery Error\n" + e.getMessage());
            alert.show();
        }
    }

    private void resetForm() throws Exception {
        adminBalanceField.clear();
        adminTransactionsField.clear();
    }
}
