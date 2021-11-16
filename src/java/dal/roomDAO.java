/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import com.mysql.jdbc.Connection;
import static dal.DataConnection.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Room;

/**
 *
 * @author MY LAPTOP
 */
public class roomDAO extends DataConnection {

    Connection connection = getConnection();

    public List<Room> getAllRoom() {
        List<Room> list = new ArrayList<>();
        String sql = "select * from Room";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Room(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getBoolean(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getDouble(9),
                        rs.getFloat(10),
                        rs.getInt(11),
                        rs.getInt(12)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Room getRoomByID(int id) {
        String sql = "select * from Room where RoomID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Room(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getBoolean(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getDouble(9),
                        rs.getFloat(10),
                        rs.getInt(11),
                        rs.getInt(12));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Room> getRoomByOwnerId(int oid) {
        List<Room> list = new ArrayList<>();
        String sql = "select * from Room \n"
                + "  where OwnerID = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, oid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Room(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getBoolean(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getDouble(9),
                        rs.getFloat(10),
                        rs.getInt(11),
                        rs.getInt(12)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void updateRoom(String rname, String desc, String owner, Boolean status, String area, String bednum, String price, String rating, String placeID, String typeID, int id) {

        String sql = "Update Room set Name=?,\n"
                + "  Description=?,\n"
                + "  OwnerID=?,\n"
                + "  Status=?,\n"
                + "  Area=?,\n"
                + "  BedNumber=?,\n"
                + "  Price=?,\n"
                + "  Rating=?,\n"
                + "  PlaceId=?,\n"
                + "  TypeId = ?\n"
                + "  where RoomID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, rname);
            st.setString(2, desc);
            st.setString(3, owner);
            st.setBoolean(4, status);
            st.setString(5, area);
            st.setString(6, bednum);
            st.setString(7, price);
            st.setString(8, rating);
            st.setString(9, placeID);
            st.setString(10, typeID);
            st.setInt(11, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void addRoom(String rname, String desc, String picture, int owner, Boolean status, String area, String bednum, String price, String placeID, String typeID) {

        String sql = "insert into Room \n"
                + "  values(?,?,?,?,?,?,?,?,0,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, rname);
            st.setString(2, desc);
            st.setString(3, picture);
            st.setInt(4, owner);
            st.setBoolean(5, status);
            st.setString(6, area);
            st.setString(7, bednum);
            st.setString(8, price);
            st.setString(9, placeID);
            st.setString(10, typeID);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void deleteRoom(String rid) {
        String sql = "delete from Room\n"
                + "  where RoomID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, rid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        roomDAO dao = new roomDAO();
        dao.addRoom("giang", "deptrai", "khong", 2, true, "70", "3", "500", "1", "1");
//        List<Room> list = dao.getRoomByOwnerId(1);
//        for (Room room : list) {
//            System.out.println(room);
//        }
//        dao.updateRoom("The Galaxy Home", "·Vị trí rất đẹp và thuận tiện ở quận Cầu Giấy\n" +
//"\n" +
//"·Gần công viên Cầu Giấy, Lotteria, trung tâm mua sắm với môi trường ngoài trời yên tĩnh\n" +
//"\n" +
//"·Bạn hoàn toàn có thể trải nghiệm những dịch vụ cao cấp tại đây", "1", "Available", "62", "1", "750000", "", "1", "2", 3);
//         Room r = dao.getRoomByOwnerId("1");
//         System.out.println();
    }
}
