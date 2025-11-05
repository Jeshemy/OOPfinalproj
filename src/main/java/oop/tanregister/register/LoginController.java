package oop.tanregister.register;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button exitButton;

    @FXML
    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.ERROR, "Missing Information", "Please fill in both fields.");
            return;
        }

        boolean found = UsersData.getUsers().stream()
                .anyMatch(u -> email.equals(u.getEmail()) && password.equals(u.getPassword()));

        if (found) {
            showAlert(AlertType.INFORMATION, "Login Successful", "Welcome back!");

            try {
                Stage currentStage = (Stage) loginButton.getScene().getWindow();

                // Correct path: update "/path/to/mainmenu.fxml" with your actual FXML path
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/tanregister/register/mainmenu.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root, 700, 550);
                currentStage.setTitle("Main Menu");
                currentStage.setScene(scene);
                currentStage.show();  // important to refresh stage view

            } catch (IOException e) {
                showAlert(AlertType.ERROR, "Error", "Could not open Main Menu:\n" + e.getMessage());
            }

        } else {
            showAlert(AlertType.ERROR, "Login Failed", "Invalid email or password.");
        }
    }

    @FXML
    private void handleExit() {
        try {
            Stage currentStage = (Stage) exitButton.getScene().getWindow();
            currentStage.close();

            // Correct path: update "/path/to/Register.fxml" to your actual FXML location
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/oop/tanregister/register/register.fxml"));
            Parent root = loader.load();

            Stage registerStage = new Stage();
            registerStage.setTitle("Register");
            registerStage.setScene(new Scene(root, 700, 550));  // set scene size consistent with your design
            registerStage.show();

        } catch (IOException e) {
            showAlert(AlertType.ERROR, "Error", "Could not return to Register page:\n" + e.getMessage());
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
