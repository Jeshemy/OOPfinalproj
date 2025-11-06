package oop.tanregister.register;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("mainmenu.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root, 650, 400);  // Match dimensions to your FXML preferred size
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // You can optionally show an alert here for user-friendly error display
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
