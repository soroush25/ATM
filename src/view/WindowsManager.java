package src.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowsManager {
    public static void showATMForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("Authentication.fxml"))
        );

        stage.setScene(scene);
        stage.setTitle("ATM");
        stage.show();
    }
}
