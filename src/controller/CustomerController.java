package src.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import src.view.WindowsManager;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
public class CustomerController implements Initializable {
    @FXML
    private Button exit, CustomerAccountBtn, CustomerTransactionBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("Entered Customer");

        CustomerAccountBtn.setOnAction(event -> {
            try {
                    Stage stage = new Stage();
                    Scene scene = new Scene(
                            FXMLLoader.load(WindowsManager.class.getResource("view/CustomerAccount.fxml"))
                    );
                    stage.setScene(scene);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error: \n" + e.getMessage());
                alert.show();
                log.error("CustomerAccount Error : " + e.getMessage());
            }
        });

        CustomerTransactionBtn.setOnAction(event -> {
            try {
                    Stage stage = new Stage();
                    Scene scene = new Scene(
                            FXMLLoader.load(WindowsManager.class.getResource("view/CustomerTransaction.fxml"))
                    );
                    stage.setScene(scene);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error: \n" + e.getMessage());
                alert.show();
                log.error("CustomerTransaction Error : " + e.getMessage());
            }
        });
        exit.setOnAction((event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Quit?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
            log.info("Quited");
        }));
    }
}
