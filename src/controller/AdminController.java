package src.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import src.model.bl.AdminBl;
import src.model.bl.CustomerBl;
import src.model.entity.Admin;
import src.model.entity.Customer;
import src.model.entity.enums.City;
import src.model.entity.enums.Gender;
import src.model.tools.Validator;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
//todo: لطفا چک شود
@Log4j
public class AdminController implements Initializable {
    @FXML
    private TextField adminSearchField, fnameField, lnameField, nidField, emailField, phoneField, addressField, UsernameField, PasswordField, IdField;

    @FXML
    private Button exit, adminCreate, adminDelete, adminEdit;

    @FXML
    private RadioButton maletoggle, femaletoggle;

    @FXML
    private TableView<Admin> adminTable;

    @FXML
    private TableColumn<Admin, Integer> adminTableAccountNumber;

    @FXML
    private TableColumn<Admin, String> adminTableAccountName, adminTableAccountBalance, adminTableAccountType;

    @FXML
    private ToggleGroup genderToggle;

    @FXML
    private ComboBox<String> cityCmb;

    @FXML
    private DatePicker birthDatePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("Entered Admin");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Admin Error\n" + e.getMessage());
            alert.show();
        }

        adminCreate.setOnAction(event -> {
            try {
                RadioButton gender = (RadioButton) genderToggle.getSelectedToggle();
                Customer customer = new Customer()
                        .builder()
                        .id(Integer.parseInt(IdField.getText()))
                        .firstName(Validator.nameValidator(fnameField.getText(), "Invalid First Name!"))
                        .lastName(Validator.nameValidator(lnameField.getText(), "Invalid Last Name!"))
                        .nationalId(Validator.nationalIDValidator(nidField.getText(), "Invalid National ID!"))
                        .gender(Gender.valueOf(gender.getText()))
                        .birthDate(birthDatePicker.getValue())
                        .email(Validator.emailValidator(emailField.getText(), "Invalid Email!"))
                        .phone(Validator.phoneValidator(phoneField.getText(), "Invalid Phone!"))
                        .address(Validator.addressValidator(addressField.getText(), "Invalid Address!"))
                        .username(UsernameField.getText())
                        .password(PasswordField.getText())
                        .city(City.valueOf(cityCmb.getSelectionModel().getSelectedItem()))
                        .build();
                CustomerBl.getCustomerBl().save(customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        adminEdit.setOnAction(event -> {
            try {
                RadioButton gender = (RadioButton) genderToggle.getSelectedToggle();
                Customer customer = new Customer()
                        .builder()
                        .id(Integer.parseInt(IdField.getText()))
                        .firstName(Validator.nameValidator(fnameField.getText(), "Invalid First Name!"))
                        .lastName(Validator.nameValidator(lnameField.getText(), "Invalid Last Name!"))
                        .nationalId(Validator.nationalIDValidator(nidField.getText(), "Invalid National ID!"))
                        .gender(Gender.valueOf(gender.getText()))
                        .birthDate(birthDatePicker.getValue())
                        .email(Validator.emailValidator(emailField.getText(), "Invalid Email!"))
                        .phone(Validator.phoneValidator(phoneField.getText(), "Invalid Phone!"))
                        .address(Validator.addressValidator(addressField.getText(), "Invalid Address!"))
                        .username(UsernameField.getText())
                        .password(PasswordField.getText())
                        .city(City.valueOf(cityCmb.getSelectionModel().getSelectedItem()))
                        .build();
                CustomerBl.getCustomerBl().edit(customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        adminDelete.setOnAction(event -> {
            try {
                CustomerBl.getCustomerBl().remove(Integer.parseInt(IdField.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted!");
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

        adminSearchField.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(Collections.singletonList(AdminBl.getAdminBl().findById(Integer.parseInt(adminSearchField.getText()))));
                showDataOnTable(AdminBl.getAdminBl().findByFamily(adminSearchField.getText()));
                showDataOnTable(Collections.singletonList(AdminBl.getAdminBl().findByNationalId(adminSearchField.getText())));
                showDataOnTable(Collections.singletonList(AdminBl.getAdminBl().findByUsername(adminSearchField.getText())));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error: \n" + e.getMessage());
                alert.show();
                log.error("Search Error: " + e.getMessage());
            }
        });

        adminTable.setOnMouseClicked((event) -> {
            Admin admin = adminTable.getSelectionModel().getSelectedItem();
            IdField.setText(String.valueOf(admin.getId()));
            fnameField.setText(admin.getFirstName());
            lnameField.setText(admin.getLastName());
            nidField.setText(admin.getNationalId());
            if (admin.getGender().equals(Gender.Male)) {
                maletoggle.setSelected(true);
            } else {
                femaletoggle.setSelected(true);
            }
            birthDatePicker.setValue(admin.getBirthDate());
            emailField.setText(admin.getEmail());
            phoneField.setText(admin.getPhone());
            cityCmb.getSelectionModel().select(admin.getCity().ordinal());
            addressField.setText(admin.getAddress());
        });
    }

    private void showDataOnTable(List<Admin> customerList) throws Exception {
        ObservableList<Admin> observableList = FXCollections.observableList(customerList);
        adminTableAccountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        adminTableAccountName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        adminTableAccountBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        adminTableAccountType.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        adminTable.setItems(observableList);
    }

    private void resetForm() throws Exception {
        IdField.clear();
        fnameField.clear();
        lnameField.clear();
        nidField.clear();
        maletoggle.setSelected(true);
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        UsernameField.clear();
        PasswordField.clear();
        birthDatePicker.setValue(null);
        cityCmb.getSelectionModel().select(0);
        showDataOnTable(AdminBl.getAdminBl().findAll());
    }
}
