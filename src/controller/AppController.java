package src.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import src.model.da.UserDa;
import src.model.entity.User;
import src.model.entity.enums.Gender;
import src.model.tools.Validator;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private TextField passcodeField, adminSearchField;

    @FXML
    private RadioButton maletoggle, femaletoggle;

    @FXML
    private ToggleGroup GenderToggle;

    @FXML
    private Button goCustomerPage, goAdminPage, passcodeBtn, goMenu, userTransfer, userBalance, userWithdrawal, userReport, adminPasscode, adminCreate, adminDelete, adminEdit, adminSearchBtn, adminBalance;

    @FXML
    private TableView<User> allTable;

    @FXML
    private TableColumn<User, Integer> adminTableID;

    @FXML
    private TableColumn<User, String> adminTableName, adminTableBalance, adminTableStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adminCreate.setOnAction(event -> {
            try (UserDa userDa = new UserDa()) {
                RadioButton gender = (RadioButton) GenderToggle.getSelectedToggle();

                User user = User
                        .builder()
                        .firstName(Validator.nameValidator(fnamefield.getText(), "Invalid Name!"))
                        .lastName(Validator.nameValidator(lnamefield.getText(), "Invalid Name!"))
                        .nationalID(Validator.nationalIDValidator(nidfield.getText(), "Invalid National ID!"))
                        .gender(Gender.valueOf(gender.getText()))
                        .email(Validator.emailValidator(emailfield.getText(), "Invalid Email!"))
                        .phone(Validator.phoneValidator(phonefield.getText(), "Invalid Phone!"))
                        .address(Validator.addressValidator(nidfield.getText(), "Invalid Address!"))
                        .build();
                userDa.save(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        adminEdit.setOnAction(event -> {
            try (UserDa userDa = new UserDa()) {
                RadioButton gender = (RadioButton) GenderToggle.getSelectedToggle();

                User user = User
                        .builder()
                        .id(Integer.parseInt(idfield.getText()))
                        .firstName(Validator.nameValidator(fnamefield.getText(), "Invalid Name!"))
                        .lastName(Validator.nameValidator(lnamefield.getText(), "Invalid Name!"))
                        .nationalID(Validator.nationalIDValidator(nidfield.getText(), "Invalid National ID!"))
                        .gender(Gender.valueOf(gender.getText()))
                        .email(Validator.emailValidator(emailfield.getText(), "Invalid Email!"))
                        .phone(Validator.phoneValidator(phonefield.getText(), "Invalid Phone!"))
                        .address(Validator.addressValidator(nidfield.getText(), "Invalid Address!"))
                        .build();
                userDa.edit(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Edited!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        adminDelete.setOnAction(event -> {
            try (UserDa userDa = new UserDa()) {
                userDa.remove(Integer.parseInt(idfield.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Deleted!");
                alert.show();
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error!\n" + e.getMessage());
                alert.show();
            }
        });

        allTable.setOnMouseClicked((event) -> {
            User user = allTable.getSelectionModel().getSelectedItem();
            idcol.setText(String.valueOf(user.getId()));
            fnamecol.setText(user.getFirstName());
            lnamecol.setText(user.getLastName());
            if (user.getGender().equals(Gender.Male)) {
                maletoggle.setSelected(true);
            } else {
                femaletoggle.setSelected(true);
            }
            emailfield.setText(user.getEmail());
            phonefield.setText(user.getPhone());
            addressfield.setText(user.getAddress());
        });
    }

    private void showDataOnTable(List<User> userList) {
        ObservableList<User> observableList = FXCollections.observableList(userList);

        adminTableID.setCellValueFactory(new PropertyValueFactory<>("id"));
        adminTableName.setCellValueFactory(new PropertyValueFactory<>("last name"));
        adminTableBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        adminTableStatus.setItems(observableList);
    }

    private void resetForm() throws Exception {
        idfield.clear();
        fnamefield.clear();
        lnamefield.clear();
        maletoggle.setSelected(true);
        emailfield.clear();
        phonefield.clear();
        addressfield.clear();
        try (UserDa userDa = new UserDa()) {
            showDataOnTable(userDa.findAll());
        }
    }
}
