package de.neuefische.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label errorLabel;

    @FXML
    protected void onLoginClick() {
        login();
    }

    @FXML
    protected void onRegisterClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register-view.fxml"));
        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(parent);
        Stage stage = (Stage) usernameInput.getScene().getWindow();
        stage.setScene(scene);
    }

    private final AuthService authService = AuthService.getInstance();

    public void login() {
        if (authService.login(usernameInput.getText(), passwordInput.getText())) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secured-view.fxml"));
            Parent parent = null;
            try {
                parent = fxmlLoader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(parent);
            Stage stage = (Stage) usernameInput.getScene().getWindow();
            stage.setScene(scene);
        } else {
            errorLabel.setText(authService.errorMessage());
        }
    }

}
