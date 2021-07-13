package ramadanhotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    private Label backHome;
    @FXML
    private Label linkBack;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

//    login successfully
    @FXML
    private void loginNow(ActionEvent event) {
        try {
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Fill All fields!!");
                alert.setTitle("Empty Fields");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else if (username.getText().matches("admin") && password.getText().matches("123")) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Login successfully!!");
                alert.setTitle("Logged!");
                alert.setHeaderText(null);
                alert.show();

                FXMLLoader form = new FXMLLoader(getClass().getResource("Admin.fxml"));
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                Scene scene = new Scene(form.load());
                stage.setScene(scene);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Account Doesnt exist");
                alert.setTitle("account Error");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void backHome(MouseEvent event) {
        try {
            FXMLLoader form = new FXMLLoader(getClass().getResource("User.fxml"));
            Stage stage = (Stage) linkBack.getScene().getWindow();
            Scene scene = new Scene(form.load());
            stage.setScene(scene);
        } catch (Exception e) {
        }
    }

}
