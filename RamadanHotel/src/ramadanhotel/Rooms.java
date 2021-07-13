
package ramadanhotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Rooms  extends HotelReserve{
    private String price;
    private String cartegory;
    private String beds;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCartegory() {
        return cartegory;
    }

    public void setCartegory(String cartegory) {
        this.cartegory = cartegory;
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

//    rooms constructor
    public Rooms(String name, String number, String beds, String hotel, String price, String cartegory) {
        this.price = price;
        this.cartegory = cartegory;
        this.beds = beds;
        this.hotel = hotel;
        this.name = name;
        this.number = number;
    }
    
//    register room functio
    public void RegisterRoom(){
         try (Connection conn = DBconnection.getConnection()) {

            // The mysql insert statement for registering the rooms
            String query = "INSERT INTO rooms(roomName, roomNo, beds, hoteName, cost, Cartegory) VALUES (?, ?, ?, ?, ?, ?)";
            // prepared statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, number);
            preparedStmt.setString(3, beds);
            preparedStmt.setString(4, hotel);
            preparedStmt.setString(5, price);
            preparedStmt.setString(6, cartegory);

            // Execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("room registered");
    }
    
    
    
    
    
}
