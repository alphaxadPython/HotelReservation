
package ramadanhotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class UserController implements Initializable {

    @FXML
    private TableView<?> ParadiseTable;
    @FXML
    private TableColumn<?, ?> roomColParadise;
    @FXML
    private TableColumn<?, ?> roomNameColParadise;
    @FXML
    private TableColumn<?, ?> cartegoryColParadise;
    @FXML
    private TableColumn<?, ?> bedsColParadise;
    @FXML
    private TableColumn<?, ?> costParadiseCol;
    @FXML
    private TableView<?> JaromaxTable;
    @FXML
    private TableColumn<?, ?> roomcolJaro;
    @FXML
    private TableColumn<?, ?> roomNameJaro;
    @FXML
    private TableColumn<?, ?> cartegoryJaroCol;
    @FXML
    private TableColumn<?, ?> bedsColJaro;
    @FXML
    private TableColumn<?, ?> costColJaro;
    @FXML
    private TableView<?> hayatTable;
    @FXML
    private TableColumn<?, ?> roomNoHayatCol;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goParadise(ActionEvent event) {
    }

    @FXML
    private void goJaromax(ActionEvent event) {
    }

    @FXML
    private void goHayat(ActionEvent event) {
    }

    @FXML
    private void goBookParadise(ActionEvent event) {
    }

    @FXML
    private void goBookJaro(ActionEvent event) {
    }
    
}
