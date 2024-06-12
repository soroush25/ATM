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
public class AdminAccountController implements Initializable {
    @FXML
    private Button exit;

    @FXML
    private TableView<Admin> AdminAccountTbl;

    @FXML
    private TableColumn<Admin, Integer> AccountNumberCol;

    @FXML
    private TableColumn<Admin, String> AccountNameCol, AccountBalanceCol, AccountTypeCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        log.info("Entered AdminAccount");
        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "AdminAccount Error\n" + e.getMessage());
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
//todo
    private void showDataOnTable(List<Admin> customerList) throws Exception {
        ObservableList<Admin> observableList = FXCollections.observableList(customerList);
        AccountNumberCol.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        AccountNameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        AccountBalanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
        AccountTypeCol.setCellValueFactory(new PropertyValueFactory<>("accountTypes"));
        AdminAccountTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        showDataOnTable(AdminBl.getAdminBl().findAll());
    }
}
