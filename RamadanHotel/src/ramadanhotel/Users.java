package ramadanhotel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Users extends HotelReserve {

    private String roomNo;
    private java.sql.Date dateIn;
    private java.sql.Date dateOut;
    private String receipt;

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Users(String hotel, String name, String number, String roomNo, Date dateIn, Date dateOut, String receipt) {
        this.roomNo = roomNo;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.receipt = receipt;
        this.hotel = hotel;
        this.name = name;
        this.number = number;
    }

    public void BookRoom() {
        try (Connection conn = DBconnection.getConnection()) {

            //insert the booking details
            String query = "INSERT INTO bookings(username, phone, dateIn, dateOut, roomNo, hotelName, receipt) VALUES (?, ?, ?, ?, ?, ?, ?)";
            // prepared statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, number);
            preparedStmt.setDate(3, dateIn);
            preparedStmt.setDate(4, dateOut);
            preparedStmt.setString(5, roomNo);
            preparedStmt.setString(6, hotel);
            preparedStmt.setString(7, receipt);

            // Execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Cannot connect the database!" + e.getMessage());
        }
        System.out.println("Booking done");
    }

}
