/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin 88
 */
public class ChartDAO extends DataConnection{
    public int getMoneyOfMonth(int month) {
        int result;
        String sql = "select sum(Total Price) from Booking where Status = 'Done' and MONTH(CheckIn) = " + month;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getRoomOfMonth(int month) {
        int result;
        String sql = "select Count(BookingID) from Booking where Status = 'Done' and MONTH(CheckIn) = " + month;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getTypeRoom(int type) {
        int result;
        String sql = "select Count(b.BookingID) from Booking b\n"
                + "  inner join Room r on r.RoomID = b.RoomID\n"
                + "  where b.Status = 'Done' and r.TypeID = " + type;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void main(String[] args) {
        ChartDAO c = new ChartDAO();
        System.out.println(c.getTypeRoom(1));
    }
}
