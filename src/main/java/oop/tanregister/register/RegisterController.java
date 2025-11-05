package oop.tanregister.register;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class RegisterController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField addressField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;
    @FXML private Button loginButton;


    // === SAVE ===
    @FXML
    private void handleSave(ActionEvent event) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneNumberField.getText().trim();
        String address = addressField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();



        // === Validation ===
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                phone.isEmpty() || address.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(AlertType.ERROR, "Missing Information", "Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(AlertType.ERROR, "Password Mismatch", "Passwords do not match.");
            return;
        }

        // === Create and save user ===
        Users newUser = new Users(
                firstName,
                "", // middle name left blank
                lastName,
                email,
                0, // no age in this form
                "", // no birthdate in this form
                phone,
                address,
                password
        );

        UsersData.addUser(newUser);

        showAlert(AlertType.INFORMATION, "Registration Successful",
                String.format("Welcome, %s %s!\nYou can now log in.", firstName, lastName));

        clearForm();

        // ✅ Optional debug confirmation
        System.out.println("✅ Total users registered: " + UsersData.getUsers().size());
    }

    // === CANCEL ===
    @FXML
    private void handleCancel(ActionEvent event) {
        clearForm();
        showAlert(AlertType.INFORMATION, "Form Cleared", "All fields have been reset.");
    }

    // === LOGIN REDIRECT ===
    @FXML
    private void handleLoginRedirect(ActionEvent event) {
        try {
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.close();

            Login loginApp = new Login();
            Stage stage = new Stage();
            loginApp.start(stage);
        } catch (Exception ex) {
            showAlert(AlertType.ERROR, "Error", "Could not open login page:\n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // === UTILITY ===
    private void clearForm() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneNumberField.clear();
        addressField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
