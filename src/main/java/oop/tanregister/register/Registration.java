package oop.tanregister.register;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Registration extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Registration.class.getResource("register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 550);
        stage.setTitle("Users");
        stage.setScene(scene);
        stage.show();
    }
}
