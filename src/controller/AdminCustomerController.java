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
import src.model.entity.Admin;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class AdminCustomerController implements Initializable {
    @FXML
    private TableView<Admin> adminCustomersTbl;

    @FXML
    private TableColumn<Admin, Integer> idCol;

    @FXML
    private TableColumn<Admin, String> nameCol, familyCol, nationalIdCol, genderCol, birthDateCol, emailCol, phoneNumberCol, addressCol, usernameCol, passwordCol, cityCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("Entered AdminCustomer");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "AdminCustomer Error\n" + e.getMessage());
            alert.show();
        }
    }

    private void showDataOnTable(List<Admin> customerList) throws Exception {
        ObservableList<Admin> observableList = FXCollections.observableList(customerList);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        familyCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        nationalIdCol.setCellValueFactory(new PropertyValueFactory<>("nationalId"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        adminCustomersTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        showDataOnTable(AdminBl.getAdminBl().findAll());
    }
}
