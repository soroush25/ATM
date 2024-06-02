package src.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import src.model.bl.CustomerBl;
import src.model.da.CustomerDa;
import src.model.entity.Account;
import src.model.entity.Admin;
import src.model.entity.Customer;
import src.model.entity.enums.Gender;
import src.model.tools.Validator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TextField passcodeField, adminSearchField, fnamefield, lnamefield, nidfield, emailfield, phonefield, addressfield, idfield;

    @FXML
    private RadioButton maletoggle, femaletoggle;

    @FXML
    private ToggleGroup genderToggle;

    @FXML
    private Button goCustomerPage, goAdminPage, passcodeBtn, goMenu, adminTransfer, adminBalance, adminWithdrawal, adminReport, adminPasscode, adminCreate, adminDelete, adminEdit, adminSearchBtn;

    @FXML
    private TableView<Admin> allTable;

    @FXML
    private TableColumn<Admin, Integer> adminTableID;

    @FXML
    private TableColumn<Admin, String> adminTableName, adminTableBalance, adminAccountType;

    @FXML
    private ComboBox citycmb;

    @FXML
    private DatePicker birthDatePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adminCreate.setOnAction(event -> {
            try (CustomerDa customerDa = new CustomerDa()) {
                RadioButton gender = (RadioButton) genderToggle.getSelectedToggle();
                Customer customer = new Customer()
                        .builder()
                        .id(Integer.parseInt(idfield.getText()))
                        .firstName(Validator.nameValidator(fnamefield.getText(), "Invalid Name!"))
                        .lastName(Validator.nameValidator(lnamefield.getText(), "Invalid Name!"))
                        .nationalId(Validator.nationalIDValidator(nidfield.getText(), "Invalid National ID!"))
                        .gender(Gender.valueOf(gender.getText()))
                        .birthDate(birthDatePicker.getValue())
                        .email(Validator.emailValidator(emailfield.getText(), "Invalid Email!"))
                        .phone(Validator.phoneValidator(phonefield.getText(), "Invalid Phone!"))
                        .address(Validator.addressValidator(addressfield.getText(), "Invalid Address!"))
                        .build();
                customerDa.save(customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        adminEdit.setOnAction(event -> {
            try (CustomerDa customerDa = new CustomerDa()) {
                RadioButton gender = (RadioButton) genderToggle.getSelectedToggle();
                Customer customer = new Customer()
                        .builder()
                        .id(Integer.parseInt(idfield.getText()))
                        .firstName(Validator.nameValidator(fnamefield.getText(), "Invalid Name!"))
                        .lastName(Validator.nameValidator(lnamefield.getText(), "Invalid Name!"))
                        .nationalId(Validator.nationalIDValidator(nidfield.getText(), "Invalid National ID!"))
                        .gender(Gender.valueOf(gender.getText()))
                        .email(Validator.emailValidator(emailfield.getText(), "Invalid Email!"))
                        .phone(Validator.phoneValidator(phonefield.getText(), "Invalid Phone!"))
                        .address(Validator.addressValidator(addressfield.getText(), "Invalid Address!"))
                        .build();
                customerDa.edit(customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        adminDelete.setOnAction(event -> {
            try (CustomerDa customerDa = new CustomerDa()) {
                customerDa.remove(Integer.parseInt(idfield.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        allTable.setOnMouseClicked((event) -> {
            Admin admin = allTable.getSelectionModel().getSelectedItem();
            Account account = allTable.getSelectionModel().getSelectedItem().getAccount();
            adminTableID.setText(String.valueOf(admin.getId()));
            adminTableName.setText(admin.getFirstName());
            adminTableName.setText(admin.getLastName());
            adminTableBalance.setText(String.valueOf(account.getAccountTypes()));
            if (admin.getGender().equals(Gender.Male)) {
                maletoggle.setSelected(true);
            } else {
                femaletoggle.setSelected(true);
            }
            emailfield.setText(admin.getEmail());
            phonefield.setText(admin.getPhone());
            addressfield.setText(admin.getAddress());
        });
    }

    private void showDataOnTable(List<Customer> customerList) throws Exception {
        ObservableList<Customer> observableList = FXCollections.observableList(customerList);
        adminTableID.setCellValueFactory(new PropertyValueFactory<>("id"));
        adminTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        adminTableBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        adminTableBalance.setCellValueFactory(new PropertyValueFactory<>("account type"));
        adminAccountType.setText(observableList.toString());
    }

    private void resetForm() throws Exception {
        idfield.clear();
        fnamefield.clear();
        lnamefield.clear();
        nidfield.clear();
        maletoggle.setSelected(true);
        emailfield.clear();
        phonefield.clear();
        addressfield.clear();
        showDataOnTable(CustomerBl.getCustomerBl().findAll());
    }
}
