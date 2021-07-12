package ramadanhotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Reception extends HotelReserve{

    private String password;

    public Reception(String hotel, String name, String number, String password) {
        this.password = password;
        this.hotel = hotel;
        this.name = name;
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void registerReception(){
         try (Connection conn = DBconnection.getConnection()) {

            // insert reception data
            String query = "INSERT INTO rooms(username, phone, password, hotelName) VALUES (?, ?, ?, ?)";
            // prepared statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, number);
            preparedStmt.setString(3, password);
            preparedStmt.setString(4, hotel);

            // Execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("reception registered");
    }

}
