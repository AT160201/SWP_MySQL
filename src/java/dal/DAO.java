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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Account;
import model.Booking;

/**
 *
 * @author MY LAPTOP
 */
public class DAO extends DataConnection{
    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String sql ="select * from Account";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    list.add(new Account(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getBoolean(8),
                            rs.getBoolean(9),
                            rs.getBoolean(10),
                            rs.getString(11),
                            rs.getBoolean(12)));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        return list;
        }
    public List<Account> searchAccountByLname(String txtSearch) {
        List<Account> list = new ArrayList<>();
        String sql ="select * from Account \n" +
                    "where LName like ? ";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1,"%" + txtSearch + "%");
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    list.add(new Account(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getBoolean(8),
                            rs.getBoolean(9),
                            rs.getBoolean(10),
                            rs.getString(11),
                            rs.getBoolean(12)));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        return list;
        }
    public Account login(String Username,String Password){
        String sql ="select * from Account \n" +
                    "  where UserName = ?\n" +
                    "  and PassWord = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Username);
            st.setString(2, Password);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getBoolean(8),
                            rs.getBoolean(9),
                            rs.getBoolean(10),
                            rs.getString(11),
                            rs.getBoolean(12));
            }    
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
        public Account checkAccount(String Username,String Phone){
        String sql ="select * from Account\n" +
                    "  where UserName = ?\n" +
                    "  or Phone = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, Username);
            st.setString(2, Phone);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getBoolean(8),
                            rs.getBoolean(9),
                            rs.getBoolean(10),
                            rs.getString(11),
                            rs.getBoolean(12));
            }    
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void signup(String UserName,String PassWord,String Email,String FName,String LName,String Phone){
        String sql ="insert into Account\n" +
                    "  values (?,?,?,?,?,?,0,1,0,null)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,UserName);
            st.setString(2,PassWord);
            st.setString(3,Email);
            st.setString(4,FName);
            st.setString(5,LName);
            st.setString(6,Phone);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public Account getAccountByID(int id){
        String sql="select * from Account where userId=?";
        try{
            PreparedStatement st= connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getBoolean(8),
                            rs.getBoolean(9),
                            rs.getBoolean(10),
                            rs.getString(11),
                            rs.getBoolean(12));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public Account checkUserName(String userName){
         String sql="select * from Account where UserName=?";
        try{
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getBoolean(8),
                            rs.getBoolean(9),
                            rs.getBoolean(10),
                            rs.getString(11),
                            rs.getBoolean(12));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }

public int updateAccount(int id,String uname, String fname, String lname, String phone, String role){
        boolean isAdmin= false, isBooker= false, isOwner=false;
            if(role.equalsIgnoreCase("Admin")) isAdmin = true;
            if(role.equalsIgnoreCase("Booker")) isBooker = true;
            if(role.equalsIgnoreCase("Owner")) isOwner = true;
            if(checkUserName(uname) ==null){
            String sql="Update Account set UserName=?, FName=?, LName=?, Phone=?, isAdmin=?, "
                    + "isBooker=?, isOwner=? where userId=?";
            try{
                PreparedStatement st= connection.prepareStatement(sql);
                st.setString(1, uname);
                st.setString(2, fname);
                st.setString(3, lname);
                st.setString(4, phone);
                st.setBoolean(5, isAdmin);
                st.setBoolean(6, isBooker);
                st.setBoolean(7, isOwner);
                st.setInt(8, id);
                return st.executeUpdate();
            }catch(SQLException ex){
                System.out.println(ex);
            }
            }
        return 0;
    }
    public int editAdmin(int id, String role){
        boolean isAdmin= false, isBooker= false, isOwner=false, isBlock=false;
            if(role.equalsIgnoreCase("Admin")) isAdmin = true;
            if(role.equalsIgnoreCase("Booker")) isBooker = true;
            if(role.equalsIgnoreCase("Owner")) isOwner = true;
            String sql="Update Account set isAdmin=?, "
                    + "isBooker=?, isOwner=?, Block=? where userId=?";
            try{
                PreparedStatement st= connection.prepareStatement(sql);
                st.setBoolean(1, isAdmin);
                st.setBoolean(2, isBooker);
                st.setBoolean(3, isOwner);
                st.setBoolean(4, isBlock);
                st.setInt(5, id);
                return st.executeUpdate();
            }catch(SQLException ex){
                System.out.println(ex);
            }
        return 0;
    }
    public void changePass(String user, String pass ) {
        String sql = "UPDATE Account SET PassWord = ? WHERE UserName = ?";
        try {
            PreparedStatement st= connection.prepareStatement(sql);           
            st.setString(1, pass);
            st.setString(2, user);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return;
    }
     private int randomNumber(int min, int max) {
        Random generator = new Random();
        return generator.nextInt((max - min) + 1) + min;
    }
    public String generatePass(){
    String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    String alphaUpperCase = alpha.toUpperCase(); // A-Z
    String digits = "0123456789"; // 0-9
    String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
 
    StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
}
    
     public Account checkUserNameEmail(String userName, String email){
         String sql="select * from Account where UserName=? and Email = ?";
        try{
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, email);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                return new Account(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getBoolean(8),
                            rs.getBoolean(9),
                            rs.getBoolean(10),
                            rs.getString(11),
                            rs.getBoolean(12));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
     
        public void insertNewBooking(Booking b) {
        String sql = "insert into Booking (IDCustomer, RoomID, CheckIn, CheckOut, Total Price, Status) values(?,?,?,?,?,?)";
        try {
            PreparedStatement st= connection.prepareStatement(sql); 
            st.setInt(1, b.getCusID());
            st.setInt(2, b.getRoom().getRid());
            st.setString(3, b.getCheckin());
            st.setString(4, b.getCheckout());
            st.setDouble(5, b.getTotal());
            st.setString(6, b.getStatus());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }        
    }
    
    public static void main(String[] args) {
        DAO dao = new DAO();
        System.out.print(dao.login("at", "123").getEmail());
}
}
