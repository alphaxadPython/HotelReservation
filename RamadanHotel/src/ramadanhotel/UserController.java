package ramadanhotel;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class UserController implements Initializable {

    @FXML
    private TableView<Rooms> ParadiseTable;
    @FXML
    private TableColumn<Rooms, String> roomColParadise;
    @FXML
    private TableColumn<Rooms, String> roomNameColParadise;
    @FXML
    private TableColumn<Rooms, String> cartegoryColParadise;
    @FXML
    private TableColumn<Rooms, String> bedsColParadise;
    @FXML
    private TableColumn<Rooms, String> costParadiseCol;
    @FXML
    private TableView<Rooms> JaromaxTable;
    @FXML
    private TableColumn<Rooms, String> roomcolJaro;
    @FXML
    private TableColumn<Rooms, String> roomNameJaro;
    @FXML
    private TableColumn<Rooms, String> cartegoryJaroCol;
    @FXML
    private TableColumn<Rooms, String> bedsColJaro;
    @FXML
    private TableColumn<Rooms, String> costColJaro;
    @FXML
    private TableView<Rooms> hayatTable;
    @FXML
    private TableColumn<Rooms, String> roomNoHayatCol;
    @FXML
    private TabPane mainTab;
    @FXML
    private TableColumn<Rooms, String> roomNameHayat;
    @FXML
    private TableColumn<Rooms, String> roomcartegoryHayat;
    @FXML
    private TableColumn<Rooms, String> bedsHayatss;
    @FXML
    private TableColumn<Rooms, String> costHayat;
    @FXML
    private TextField usernameBook;
    @FXML
    private TextField phoneBook;
    @FXML
    private DatePicker dateIn;
    @FXML
    private DatePicker DateOut;
    @FXML
    private TextField ReceiptBook;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            paradiseTable();
            hayatTableList();
            jaroMaxTable();
        } catch (Exception e) {
        }
    }

    @FXML
    private void goParadise(ActionEvent event) {
        mainTab.getSelectionModel().select(1);

    }

    @FXML
    private void goJaromax(ActionEvent event) {
        mainTab.getSelectionModel().select(2);

    }

    @FXML
    private void goHayat(ActionEvent event) {
        mainTab.getSelectionModel().select(3);

    }

    @FXML
    private void goBook(ActionEvent event) {
        mainTab.getSelectionModel().select(4);

    }

    //   fetch paradise rooms
    public ObservableList<Rooms> getRoomList() throws SQLException {
        ObservableList<Rooms> roomList = FXCollections.observableArrayList();
        Connection conn = DBconnection.getConnection();
        String query = "Select * from rooms where hoteName='Paradise hotel'";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Rooms room;
            while (rs.next()) {
                room = new Rooms(rs.getString("roomName"), rs.getString("roomNo"), rs.getString("beds"), rs.getString("hoteName"), rs.getString("cost"), rs.getString("cartegory"));
                roomList.add(room);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return roomList;
    }

//   assign data to table showing paradise details
    public void paradiseTable() throws SQLException {
        ObservableList<Rooms> list = (ObservableList<Rooms>) getRoomList();
        roomColParadise.setCellValueFactory(new PropertyValueFactory<Rooms, String>("number"));
        roomNameColParadise.setCellValueFactory(new PropertyValueFactory<Rooms, String>("name"));
        bedsColParadise.setCellValueFactory(new PropertyValueFactory<Rooms, String>("beds"));
        cartegoryColParadise.setCellValueFactory(new PropertyValueFactory<Rooms, String>("cartegory"));
        costParadiseCol.setCellValueFactory(new PropertyValueFactory<Rooms, String>("price"));

        ParadiseTable.setItems(list);
    }

    //   fetch paradise rooms
    public ObservableList<Rooms> getHayatList() throws SQLException {
        ObservableList<Rooms> roomList = FXCollections.observableArrayList();
        Connection conn = DBconnection.getConnection();
        String query = "Select * from rooms where hoteName='Hayat Hill hotel'";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Rooms room;
            while (rs.next()) {
                room = new Rooms(rs.getString("roomName"), rs.getString("roomNo"), rs.getString("beds"), rs.getString("hoteName"), rs.getString("cost"), rs.getString("cartegory"));
                roomList.add(room);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return roomList;
    }

//   assign data to table showing paradise details
    public void hayatTableList() throws SQLException {
        ObservableList<Rooms> list = (ObservableList<Rooms>) getHayatList();
        roomNoHayatCol.setCellValueFactory(new PropertyValueFactory<Rooms, String>("number"));
        roomNameHayat.setCellValueFactory(new PropertyValueFactory<Rooms, String>("name"));
        bedsHayatss.setCellValueFactory(new PropertyValueFactory<Rooms, String>("beds"));
        roomcartegoryHayat.setCellValueFactory(new PropertyValueFactory<Rooms, String>("cartegory"));
        costHayat.setCellValueFactory(new PropertyValueFactory<Rooms, String>("price"));

        hayatTable.setItems(list);
    }

    //   fetch data for jalomax
    public ObservableList<Rooms> getJaromax() throws SQLException {
        ObservableList<Rooms> roomList = FXCollections.observableArrayList();
        Connection conn = DBconnection.getConnection();
        String query = "Select * from rooms where hoteName='Jaromax Hotel'";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Rooms room;
            while (rs.next()) {
                room = new Rooms(rs.getString("roomName"), rs.getString("roomNo"), rs.getString("beds"), rs.getString("hoteName"), rs.getString("cost"), rs.getString("cartegory"));
                roomList.add(room);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return roomList;
    }

//  assign data to jaromax table
    public void jaroMaxTable() throws SQLException {
        ObservableList<Rooms> list = (ObservableList<Rooms>) getJaromax();
        roomcolJaro.setCellValueFactory(new PropertyValueFactory<Rooms, String>("number"));
        roomNameJaro.setCellValueFactory(new PropertyValueFactory<Rooms, String>("name"));
        bedsColJaro.setCellValueFactory(new PropertyValueFactory<Rooms, String>("beds"));
        cartegoryJaroCol.setCellValueFactory(new PropertyValueFactory<Rooms, String>("cartegory"));
        costColJaro.setCellValueFactory(new PropertyValueFactory<Rooms, String>("price"));

        JaromaxTable.setItems(list);
    }

   

//    booking method here
    @FXML
    private void makeBoking(ActionEvent event) {
        try {
            if (usernameBook.getText().isEmpty() || phoneBook.getText().isEmpty() || ReceiptBook.getText().isEmpty() || dateIn.getValue().isEqual(null) || DateOut.getValue().isEqual(null)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Fill All fields!!");
                alert.setTitle("Empty Fields");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {

                String user = usernameBook.getText();
                String phn = phoneBook.getText();
                String rcpt = ReceiptBook.getText();
                java.sql.Date datIn = java.sql.Date.valueOf(dateIn.getValue().toString());
                java.sql.Date datOut = java.sql.Date.valueOf(DateOut.getValue().toString());

                Users newBooking = new Users(user, phn, datIn, datOut, rcpt);
                newBooking.BookRoom();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Booking made successFully!!");
                alert.setTitle("Booked");
                alert.setHeaderText(null);
                alert.show();

            }
        } catch (Exception e) {
        }
    }

}
