/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import static dal.DataConnection.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Booking;
import model.Room;

/**
 *
 * @author ADMIN
 */
public class BookerDAO extends DataConnection {

    public List<Booking> getAllBooking(int uid, String search,String status, String orderBy){
        Connection connection= getConnection();
        List<Booking> list= new ArrayList<>();
        String sql= "select * from Booking join Room on Booking.RoomID= Room.RoomID where IDCustomer= "+uid;
        if(search !=null){
            sql += " and room.Name like N'%"+search+"%'";
        }
        if(!status.equalsIgnoreCase("0")){
           sql += " and Booking.Status like '"+status+"'"; 
        }
        if(!orderBy.equalsIgnoreCase("0")){
            sql += " order by "+orderBy;
        }
        try{
            Statement st= (Statement)connection.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while(rs.next()){
                Room room= new Room(rs.getInt("RoomID"), rs.getString("Name"), rs.getString("Description"),
                rs.getString("Picture"), rs.getInt("OwnerID"), rs.getBoolean("Status"), rs.getInt("Area"), 
                        rs.getInt("BedNumber"), rs.getDouble("Price"), rs.getFloat("Rating"), rs.getInt("PlaceID"),
                rs.getInt("TypeID"));
                list.add(new Booking(rs.getInt("BookingID"),rs.getInt("IDCustomer"), room,
                rs.getString("CheckIn"), rs.getString("CheckOut"), rs.getDouble("TotalPrice"),
                rs.getString("Status")));
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return list;
    }
    public static void main(String[] args){
        BookerDAO db= new BookerDAO();
        System.out.println(db.getAllBooking(2, null, "0", "price desc").size());
    }
}
