package src.controller;

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
public class AuthenticationController implements Initializable {
    @FXML
    private Button adminBtn, customerBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("Start");

        adminBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(WindowsManager.class.getResource("../view/AdminAuthentication.fxml"))
                );
                stage.setScene(scene);
                stage.show();
                adminBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error: \n" + e.getMessage());
                alert.show();
                log.error("Login Error: " + e.getMessage());
            }
        });

        customerBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(WindowsManager.class.getResource("../view/CustomerAuthentication.fxml"))
                );
                stage.setScene(scene);
                stage.show();
                customerBtn.getScene().getWindow().hide();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error: \n" + e.getMessage());
                alert.show();
                log.error("Login Error: " + e.getMessage());
            }
        });
    }
}