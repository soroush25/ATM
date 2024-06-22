package src.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import src.model.bl.AdminBl;
import src.model.entity.AppData;
import src.view.WindowsManager;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
public class AdminAuthenticationController implements Initializable {
    @FXML
    private TextField usernameField, passwordField;

    @FXML
    private Button loginBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("AdminLogin");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "AdminLogin Error\n" + e.getMessage());
            alert.show();
        }

        loginBtn.setOnAction(event -> {
            try {
                AppData.admin = AdminBl.getAdminBl().findByUsernameAndPassword(usernameField.getText(), passwordField.getText());
                if (AppData.admin != null) {
                    Stage stage = new Stage();
                    Scene scene = new Scene(
                            FXMLLoader.load(WindowsManager.class.getResource("../view/Admin.fxml"))
                    );
                    stage.setScene(scene);
                    stage.show();
                    loginBtn.getScene().getWindow().hide();
                    System.out.println(AppData.admin);
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error: \n" + e.getMessage());
                alert.show();
                log.error("Login Error: " + e.getMessage());
            }
        });
    }

    private void resetForm() {
        usernameField.clear();
        passwordField.clear();
    }
}