package src.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.log4j.Log4j;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
public class AdminSummeryController implements Initializable {
    @FXML
    private TextField AdminBalanceField, AdminTransactionsField;

    @FXML
    private Button exit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("Entered AdminSummery");
        try { //todo: اشکال
            resetForm();
            AdminBalanceField.setText("");
            AdminTransactionsField.setText("");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "AdminSummery Error\n" + e.getMessage());
            alert.show();
        }

        exit.setOnAction((event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Quit?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
            log.info("Quited");
        }));
    }

    private void resetForm() throws Exception {
        AdminBalanceField.clear();
        AdminTransactionsField.clear();
    }
}
