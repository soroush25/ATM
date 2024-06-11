package src.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import src.model.bl.CustomerBl;
import src.model.entity.AppData;
import src.model.entity.Customer;
import src.model.entity.enums.City;
import src.view.WindowsManager;

import java.net.URL;
import java.util.ResourceBundle;

//todo: لطفا چک شود
@Log4j
public class CustomerController implements Initializable {
    @FXML
    private Label welcomeLbl;

    @FXML
    private TextField firstNameField, lastNameField, emailField, phoneField,addressField,usernameField,passwordField;

    @FXML
    private ComboBox<City> cityCmb;

    @FXML
    private Button exit, customerAccountBtn, customerTransactionBtn, editBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Entered Customer");
        for (City city : City.values()) {
            cityCmb.getItems().add(city);
        }

        cityCmb.getSelectionModel().select(0);

        welcomeLbl.setText("Welcome " +AppData.customer.getFirstName() + " " + AppData.customer.getLastName());

        firstNameField.setText(AppData.customer.getFirstName());
        lastNameField.setText(AppData.customer.getLastName());
        emailField.setText(AppData.customer.getEmail());
        phoneField.setText(AppData.customer.getPhone());
        usernameField.setText(AppData.customer.getUsername());
        passwordField.setText(AppData.customer.getPassword());
        addressField.setText(AppData.customer.getAddress());


        editBtn.setOnAction(event->{
            try {
            Customer customer =
                    Customer
                            .builder()
                            .id(AppData.customer.getId())
                            .firstName(firstNameField.getText())
                            .lastName(lastNameField.getText())
                            .email(emailField.getText())
                            .city(cityCmb.getSelectionModel().getSelectedItem())
                            .username(usernameField.getText())
                            .password(passwordField.getText())
                            .build();

                CustomerBl.getCustomerBl().edit(customer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

//            todo
//            alert
        });

        customerAccountBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(WindowsManager.class.getResource("../view/CustomerAccount.fxml"))
                );
                stage.setScene(scene);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error: \n" + e.getMessage());
                alert.show();
                log.error("CustomerAccount Error : " + e.getMessage());
            }
        });

        customerTransactionBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(WindowsManager.class.getResource("../view/CustomerTransaction.fxml"))
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
