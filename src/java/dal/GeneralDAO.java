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
import model.Place;
import model.Room;
import model.Type;

/**
 *
 * @author ADMIN
 */
public class GeneralDAO extends DataConnection {

    private Connection connection = getConnection();

    public ArrayList<Place> getTop6Place() {
        ArrayList<Place> list = new ArrayList<>();
        String sql = "select p.PlaceID, p.Place, p.img, COUNT(RoomID) as count from Place as p inner join Room \n"
                + "as r on p.PlaceID= r.PlaceID group by p.PlaceID, p.Place, p.img order by count desc\n"
                + "limit 6";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Place(rs.getInt("PlaceID"), rs.getString("Place"), rs.getString("img")));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<Type> getTop6Type() {
        ArrayList<Type> list = new ArrayList<>();
        String sql = "select p.TypeID, p.Type, p.img, COUNT(RoomID) as count from Type as p inner join Room "
                + "as r on p.TypeID= r.TypeID group by p.TypeID, p.Type, p.img order by count desc "
                + "limit 6";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Type(rs.getInt("TypeID"), rs.getString("Type"), rs.getString("img")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public ArrayList<Place> getAllPlace() {
        ArrayList<Place> list = new ArrayList<>();
        String sql = "Select * from Place";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Place(rs.getInt("PlaceID"), rs.getString("Place"), rs.getString("img")));
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public ArrayList<Type> getAllType() {
        ArrayList<Type> list = new ArrayList<>();
        String sql = "Select * from Type";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Type(rs.getInt("TypeID"), rs.getString("Type"), rs.getString("img")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public ArrayList<Room> get6Room() {
        ArrayList<Room> list = new ArrayList<>();
        String sql = "select * from Room order by Rating limit 6";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Room(rs.getInt("RoomID"), rs.getString("Name"), rs.getString("Description"),
                        rs.getString("Picture"), rs.getInt("OwnerID"), rs.getBoolean("Status"), rs.getInt("Area"),
                        rs.getInt("BedNumber"), rs.getDouble("Price"), rs.getFloat("Rating"), rs.getInt("PlaceID"),
                        rs.getInt("TypeID")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public int[] numberOfBed() {
        int[] a = new int[100];
        String sql = "select distinct BedNumber from Room ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                a[a.length] = rs.getInt("BedNumber");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return a;
    }

    public void AddPlace(String pName, String pimg) {
        String sql = "insert into Place\n"
                + "  values(?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pName);
            st.setString(2, pimg);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void AddType(String tName, String timg) {
        String sql = "insert into Type\n"
                + "  values(?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, tName);
            st.setString(2, timg);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Room> searchRoom(String placeId, String typeId, String bedNum, String search, String orderBy) {
        List<Room> list = new ArrayList<>();
        String sql = "select * from Room where 1=1 ";
        if (Integer.parseInt(placeId) > 0) {
            sql += " and PlaceID=" + placeId;
        }
        if (Integer.parseInt(typeId) > 0) {
            sql += " and TypeID=" + typeId;
        }
        if (Integer.parseInt(bedNum) > 0) {
            sql += " and BedNumber=" + bedNum;
        }
        if (search != null) {
            sql += " and room.Name like N'%" + search + "%'";
        }
        if (!orderBy.equalsIgnoreCase("0")) {
            sql += " order by " + orderBy;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Room(rs.getInt("RoomID"), rs.getString("Name"), rs.getString("Description"),
                        rs.getString("Picture"), rs.getInt("OwnerID"), rs.getBoolean("Status"), rs.getInt("Area"),
                        rs.getInt("BedNumber"), rs.getDouble("Price"), rs.getFloat("Rating"), rs.getInt("PlaceID"),
                        rs.getInt("TypeID")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
//    public ArrayList<Feedback> get3Feedback(){
//        ArrayList<Feedback> list= new ArrayList<>();
//        return list;
//    }

    public Place getPlaceById(int pid) {
        String sql = "Select * from Place where PlaceID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Place(rs.getInt("PlaceID"), rs.getString("Place"),
                        rs.getString("img"));
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        }
        return null;
    }

    public Type getTypeById(int tid) {
        String sql = "Select * from Type where TypeID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, tid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Type(rs.getInt("TypeID"), rs.getString("Type"),
                        rs.getString("img"));
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        }
        return null;
    }

    public List<Room> getRoonPlace(int pid, String search, String sort) {
        List<Room> list = new ArrayList<>();
        String sql = "Select * from Room where PlaceID=?";
        if (search != null) {
            sql += " and Name like N'%" + search + "%'";
        }
        if (!sort.equalsIgnoreCase("0")) {
            sql += " order by " + sort;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Room(rs.getInt("RoomID"), rs.getString("Name"), rs.getString("Description"),
                        rs.getString("Picture"), rs.getInt("OwnerID"), rs.getBoolean("Status"), rs.getInt("Area"),
                        rs.getInt("BedNumber"), rs.getDouble("Price"), rs.getFloat("Rating"), rs.getInt("PlaceID"),
                        rs.getInt("TypeID")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Room> getRoonType(int tid, String search, String sort) {
        List<Room> list = new ArrayList<>();
        String sql = "Select * from Room where TypeID=?";
        if (search != null) {
            sql += " and Name like N'%" + search + "%'";
        }
        if (!sort.equalsIgnoreCase("0")) {
            sql += " order by " + sort;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, tid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Room(rs.getInt("RoomID"), rs.getString("Name"), rs.getString("Description"),
                        rs.getString("Picture"), rs.getInt("OwnerID"), rs.getBoolean("Status"), rs.getInt("Area"),
                        rs.getInt("BedNumber"), rs.getDouble("Price"), rs.getFloat("Rating"), rs.getInt("PlaceID"),
                        rs.getInt("TypeID")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public static void main(String[] args) {
        GeneralDAO db = new GeneralDAO();
        int[] arr = db.numberOfBed();
        System.out.println(arr[1]);
    }
}
