package de.neuefische.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

public class SecuredController implements Initializable {
    @FXML
    public Text catAnswerText;
    @FXML
    public Text catIdAnswerText;
    @FXML
    public TextField inputCatId;

    @FXML
    protected void onGetCatClick() {
        getCatById();
    }

    @FXML
    protected void onLogoutClick() {
        if (authService.logout()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent parent = null;
            try {
                parent = fxmlLoader.load();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(parent);
            Stage stage = (Stage) catAnswerText.getScene().getWindow();
            stage.setScene(scene);
        } else {
            System.out.println("Logout failed");
        }
    }

    private final AuthService authService = AuthService.getInstance();

    public void getCats() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/cats"))
                .header("Cookie", "JSESSIONID=" + authService.sessionId())
                .GET()
                .build();

        var response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        catAnswerText.setText(response);
    }

    public void getCatById() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/cats/" + inputCatId.getText()))
                .header("Cookie", "JSESSIONID=" + authService.sessionId())
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        var response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        catIdAnswerText.setText(response);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getCats();
    }
}
