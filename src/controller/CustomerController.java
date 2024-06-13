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
import src.model.entity.enums.Gender;
import src.model.tools.Validator;
import src.view.WindowsManager;

import java.net.URL;
import java.util.ResourceBundle;

@Log4j
public class CustomerController implements Initializable {
    @FXML
    private Label welcomeLbl;

    @FXML
    private TextField idField, fnameField, lnameField, nidField, emailField, phoneField, addressField, usernameField, passwordField;

    @FXML
    private RadioButton maleToggle, femaleToggle;

    @FXML
    private ComboBox<City> cityCmb;

    @FXML
    private ToggleGroup genderToggle;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private Button exit, customerAccountBtn, customerTransactionBtn, editBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Entered Customer");
        for (City city : City.values()) {
            cityCmb.getItems().add(city);
        }

        cityCmb.getSelectionModel().select(0);

        welcomeLbl.setText("Welcome " + AppData.customer.getFirstName() + " " + AppData.customer.getLastName());

        idField.setText(String.valueOf(AppData.customer.getId()));
        fnameField.setText(AppData.customer.getFirstName());
        lnameField.setText(AppData.customer.getLastName());
        nidField.setText(AppData.customer.getNationalId());
        if (AppData.customer.getGender().equals(Gender.Male)) {
            maleToggle.setSelected(true);
        } else {
            femaleToggle.setSelected(true);
        }
        birthDatePicker.setValue(AppData.customer.getBirthDate());
        emailField.setText(AppData.customer.getEmail());
        phoneField.setText(AppData.customer.getPhone());
        cityCmb.getSelectionModel().select(AppData.customer.getCity().ordinal());
        addressField.setText(AppData.customer.getAddress());
        usernameField.setText(AppData.customer.getUsername());
        passwordField.setText(AppData.customer.getPassword());


        editBtn.setOnAction(event->{
            try {
                RadioButton gender = (RadioButton) genderToggle.getSelectedToggle();
                Customer customer = Customer
                        .builder()
                        .id(AppData.customer.getId())
                        .firstName(Validator.nameValidator(fnameField.getText(), "Invalid First Name!"))
                        .lastName(Validator.nameValidator(lnameField.getText(), "Invalid Last Name!"))
                        .nationalId(Validator.nationalIDValidator(nidField.getText(), "Invalid National ID!"))
                        .gender(Gender.valueOf(gender.getText()))
                        .birthDate(birthDatePicker.getValue())
                        .email(Validator.emailValidator(emailField.getText(), "Invalid Email!"))
                        .phone(Validator.phoneValidator(phoneField.getText(), "Invalid Phone!"))
                        .city(cityCmb.getSelectionModel().getSelectedItem())
                        .address(Validator.addressValidator(addressField.getText(), "Invalid Address!"))
                        .username(usernameField.getText())
                        .password(passwordField.getText())
                        .build();

                CustomerBl.getCustomerBl().edit(customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited!");
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        customerAccountBtn.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(
                        FXMLLoader.load(WindowsManager.class.getResource("../view/CustomerAccount.fxml"))
                );
                stage.setScene(scene);
                stage.show();
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
                stage.show();
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
