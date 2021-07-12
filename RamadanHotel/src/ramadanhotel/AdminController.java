package ramadanhotel;

import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController implements Initializable {

    @FXML
    private TextField roomName;
    @FXML
    private TextField roomNo;
    @FXML
    private ComboBox<String> HotelCombo;
    @FXML
    private TextField Beds;
    @FXML
    private TextField Price;
    @FXML
    private ComboBox<String> cartegoryCombo;
    @FXML
    private TableView<Rooms> roomtable;
    @FXML
    private TableColumn<Rooms, String> hotelCol;
    @FXML
    private TableColumn<Rooms, String> roomNoCol;
    @FXML
    private TableColumn<Rooms, String> roomNameCol;
    @FXML
    private TableColumn<Rooms, String> BedsCol;
    @FXML
    private TableColumn<Rooms, String> Cartegorycol;
    @FXML
    private TableColumn<Rooms, String> PriceCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        combo box for the hotels
        ObservableList<String> hotel = FXCollections.observableArrayList(
                "Paradise hotel",
                "Hayat Hill hotel",
                "Jaromax Hotel"
        );
        HotelCombo.setItems(hotel);

//        combo box for rooms
        ObservableList<String> rooms = FXCollections.observableArrayList(
                "Standard Double Room",
                "Delux Double Room",
                "Executive Room",
                "Standard Twin Room"
        );
        cartegoryCombo.setItems(rooms);
        
        
        try {
            roomsTable();
        } catch (Exception e) {
        }
    }

    @FXML
    private void registerRooms(ActionEvent event) {
        try {
            if (roomName.getText().isEmpty() || roomNo.getText().isEmpty() || HotelCombo.getValue().isEmpty() || Beds.getText().isEmpty() || Price.getText().isEmpty() || cartegoryCombo.getValue().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Fill All fields!!");
                alert.setTitle("Empty Fields");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {

                String room = roomName.getText();
                String rmNo = roomNo.getText();
                String hotel = HotelCombo.getValue();
                String beds = Beds.getText();
                String price = Price.getText();
                String cartegory = cartegoryCombo.getValue();

                Rooms newRoom = new Rooms(room, rmNo, beds, hotel, price, cartegory);
                newRoom.RegisterRoom();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Room registered successFully!!");
                alert.setTitle("Registered Room");
                alert.setHeaderText(null);
                alert.show();

                roomName.setText("");
                roomNo.setText("");
                HotelCombo.setValue(null);
                Beds.setText("");
                Price.setText("");
                cartegoryCombo.setValue(null);

            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void UpdateRoom(ActionEvent event) {
    }

    @FXML
    private void deleteRoom(ActionEvent event) {
    }
    
    //   fetch rooms data to admin
    public ObservableList<Rooms> getRoomList() throws SQLException {
        ObservableList<Rooms> roomList = FXCollections.observableArrayList();
        Connection conn = DBconnection.getConnection();
        String query = "Select * from rooms";
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
//    assign events in table

//   assign data to rooms table
    public void roomsTable() throws SQLException {
        ObservableList<Rooms> list = (ObservableList<Rooms>) getRoomList();
        hotelCol.setCellValueFactory(new PropertyValueFactory<Rooms, String>("hotel"));
        roomNoCol.setCellValueFactory(new PropertyValueFactory<Rooms, String>("number"));
        roomNameCol.setCellValueFactory(new PropertyValueFactory<Rooms, String>("name"));
        BedsCol.setCellValueFactory(new PropertyValueFactory<Rooms, String>("beds"));
        Cartegorycol.setCellValueFactory(new PropertyValueFactory<Rooms, String>("cartegory"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<Rooms, String>("price"));

        roomtable.setItems(list);
    }
}
