package mft.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowsManager {
    public static void showPersonForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("view/profile.fxml"))
        );

        stage.setScene(scene);
        stage.setTitle("Profile");
        stage.show();
    }

    public static void showStuffForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("view/stuff.fxml"))
        );

        stage.setScene(scene);
        stage.setTitle("Profile");
        stage.show();
    }

    public static void showAboutForm() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(
                FXMLLoader.load(WindowsManager.class.getResource("about.fxml"))
        );

        stage.setScene(scene);
        stage.setTitle("About");
        stage.show();
    }
}
