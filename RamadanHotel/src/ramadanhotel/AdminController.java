package ramadanhotel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
    @FXML
    private TableView<?> bokingsTable;
    @FXML
    private TableColumn<?, ?> usernameBookingCol;
    @FXML
    private TableColumn<?, ?> bookingPhone;
    @FXML
    private TableColumn<?, ?> hotelBookingCol;
    @FXML
    private TableColumn<?, ?> roomNoBookingCol;
    @FXML
    private TableColumn<?, ?> DateInBookingCol;
    @FXML
    private TableColumn<?, ?> DateOutCol;
    @FXML
    private TableColumn<?, ?> receiptCol;
    @FXML
    private TextField username;
    @FXML
    private ComboBox<String> hotelName;
    @FXML
    private PasswordField password;
    @FXML
    private TextField phone;
    @FXML
    private TableView<Reception> receptionTable;
    @FXML
    private TableColumn<Reception, String> usernameColReception;
    @FXML
    private TableColumn<Reception, String> hotelColreception;
    @FXML
    private TableColumn<Reception, String> passwordColReception;
    @FXML
    private TableColumn<Reception, String> phoneCollRception;
    @FXML
    private TabPane adminTab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        combo box for the hotels
        ObservableList<String> hotel = FXCollections.observableArrayList(
                "Paradise hotel",
                "Hayat Hill hotel",
                "Jaromax Hotel"
        );
        HotelCombo.setItems(hotel);
        hotelName.setItems(hotel);

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
            receptionTable();
            bookingTable();
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

//                object creation
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
                roomsTable();

            }
        } catch (Exception e) {
        }
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

    public String Name;
//    onclick pull data to text boxes

    @FXML
    private void selectData(MouseEvent event) {
        if (event.getClickCount() == 2) {
            roomName.setText(roomtable.getSelectionModel().getSelectedItem().name);
            roomNo.setText(roomtable.getSelectionModel().getSelectedItem().number);
            Price.setText(roomtable.getSelectionModel().getSelectedItem().getPrice());
            HotelCombo.setValue(roomtable.getSelectionModel().getSelectedItem().hotel);
            cartegoryCombo.setValue(roomtable.getSelectionModel().getSelectedItem().getCartegory());
            Beds.setText(roomtable.getSelectionModel().getSelectedItem().getBeds());
            //            setting the name to capture updates and delete
            Name = roomtable.getSelectionModel().getSelectedItem().name;

        }
    }

//    update room here
    @FXML
    private void UpdateRoom(ActionEvent event) {
        try (Connection conn = DBconnection.getConnection()) {

            // updating the rooms details
            String query = "UPDATE rooms SET roomName=?, roomNo=?, beds=?, hoteName=?, cost=?, cartegory=? WHERE roomName=?";

            // Create the mysql insert prepared statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, roomName.getText());
            preparedStmt.setString(2, roomNo.getText());
            preparedStmt.setString(3, Beds.getText());
            preparedStmt.setString(4, HotelCombo.getValue());
            preparedStmt.setString(5, Price.getText());
            preparedStmt.setString(6, cartegoryCombo.getValue());
            preparedStmt.setString(7, Name);

            // Execute the preparedstatement
            preparedStmt.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Updated Successfully!!!!");
            alert.setTitle("Updates");
            alert.setHeaderText(null);
            alert.show();

            roomName.setText("");
            roomNo.setText("");
            HotelCombo.setValue(null);
            Beds.setText("");
            Price.setText("");
            cartegoryCombo.setValue(null);
            roomsTable();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("updated room");
    }

//    deleting room here
    @FXML
    private void deleteRoom(ActionEvent event) {
        try (Connection conn = DBconnection.getConnection()) {

            // delete query
            String query = "DELETE FROM rooms WHERE roomName=?";
            // deleting the rooms
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, Name);

            // Execute the preparedstatement
            preparedStmt.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deleted Successfully!!");
            alert.setTitle("Deleted");
            alert.setHeaderText(null);
            alert.show();

            roomName.setText("");
            roomNo.setText("");
            HotelCombo.setValue(null);
            Beds.setText("");
            Price.setText("");
            cartegoryCombo.setValue(null);
            roomsTable();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("Deleting the Event");
    }

//    register reception
    @FXML
    private void registerReception(ActionEvent event) {
        try {
            if (username.getText().isEmpty() || hotelName.getValue().isEmpty() || password.getText().isEmpty() || phone.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please Fill All fields!!");
                alert.setTitle("Empty Fields");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {

                String user = username.getText();
                String hotel = hotelName.getValue();
                String pwd = password.getText();
                String phon = phone.getText();

//                object
                Reception newReception = new Reception(user, phon, pwd, hotel);
                newReception.registerReception();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Reception registered successFully!!");
                alert.setTitle("Registered Reception");
                alert.setHeaderText(null);
                alert.show();

                username.setText("");
                hotelName.setValue(null);
                password.setText("");
                phone.setText("");
                receptionTable();

            }
        } catch (Exception e) {
        }
    }

    //   fetch recepton data to admin
    public ObservableList<Reception> getReceptionList() throws SQLException {
        ObservableList<Reception> receptionList = FXCollections.observableArrayList();
        Connection conn = DBconnection.getConnection();
        String query = "Select * from reception";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Reception receipt;
            while (rs.next()) {
                receipt = new Reception(rs.getString("username"), rs.getString("phone"), rs.getString("password"), rs.getString("hotelName"));
                receptionList.add(receipt);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return receptionList;
    }

//   assign data to reception table
    public void receptionTable() throws SQLException {
        ObservableList<Reception> list = (ObservableList<Reception>) getReceptionList();
        usernameColReception.setCellValueFactory(new PropertyValueFactory<Reception, String>("name"));
        hotelColreception.setCellValueFactory(new PropertyValueFactory<Reception, String>("hotel"));
        passwordColReception.setCellValueFactory(new PropertyValueFactory<Reception, String>("password"));
        phoneCollRception.setCellValueFactory(new PropertyValueFactory<Reception, String>("number"));
        receptionTable.setItems(list);
    }

//    capture reception data
    @FXML
    private void captureReception(MouseEvent event) {
        if (event.getClickCount() == 2) {
            username.setText(receptionTable.getSelectionModel().getSelectedItem().name);
            password.setText(receptionTable.getSelectionModel().getSelectedItem().getPassword());
            hotelName.setValue(receptionTable.getSelectionModel().getSelectedItem().hotel);
            phone.setText(receptionTable.getSelectionModel().getSelectedItem().getNumber());

            //            setting the name to capture updates and delete
            Name = receptionTable.getSelectionModel().getSelectedItem().name;

        }
    }

//    update reception
    @FXML
    private void updateReceprtion(ActionEvent event) {
        try (Connection conn = DBconnection.getConnection()) {

            // updating query
            String query = "UPDATE reception set username=?, phone=?, password=?, hotelName=? WHERE username=?";

            // update statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, username.getText());
            preparedStmt.setString(2, phone.getText());
            preparedStmt.setString(3, password.getText());
            preparedStmt.setString(4, hotelName.getValue());
            preparedStmt.setString(5, Name);

            // Execute the preparedstatement
            preparedStmt.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Updated Successfully!!!!");
            alert.setTitle("Updates");
            alert.setHeaderText(null);
            alert.show();

            username.setText("");
            hotelName.setValue(null);
            password.setText("");
            phone.setText("");
            receptionTable();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("updated reception");
    }

//    delete reception
    @FXML
    private void deleteReception(ActionEvent event) {
        try (Connection conn = DBconnection.getConnection()) {

            // deleting reception
            String query = "DELETE FROM reception WHERE username=?";

            // statements here
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, Name);

            // Execute the preparedstatement
            preparedStmt.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Deleted Successfully!!!!");
            alert.setTitle("Deleted");
            alert.setHeaderText(null);
            alert.show();

            username.setText("");
            hotelName.setValue(null);
            password.setText("");
            phone.setText("");
            receptionTable();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("updated reception");
    }

    @FXML
    private void goRooms(ActionEvent event) {
        adminTab.getSelectionModel().select(0);
    }

    @FXML
    private void goReception(ActionEvent event) {
        adminTab.getSelectionModel().select(2);

    }

    @FXML
    private void goBookings(ActionEvent event) {
        adminTab.getSelectionModel().select(1);

    }

    
        //   fetch recepton data to admin
    public ObservableList<Reception> getBookingList() throws SQLException {
        ObservableList<Reception> bookingList = FXCollections.observableArrayList();
        Connection conn = DBconnection.getConnection();
        String query = "Select * from bookings";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Reception receipt;
            while (rs.next()) {
                receipt = new Reception(rs.getString("username"), rs.getString("phone"), rs.getString("dateIn"), rs.getString("dateOut"));
                bookingList.add(receipt);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bookingList;
    }

//   assign data to reception table
    public void bookingTable() throws SQLException {
        ObservableList<Reception> list = (ObservableList<Reception>) getBookingList();
        usernameColReception.setCellValueFactory(new PropertyValueFactory<Reception, String>("name"));
        hotelColreception.setCellValueFactory(new PropertyValueFactory<Reception, String>("hotel"));
        passwordColReception.setCellValueFactory(new PropertyValueFactory<Reception, String>("password"));
        phoneCollRception.setCellValueFactory(new PropertyValueFactory<Reception, String>("number"));
        receptionTable.setItems(list);
    }

}
