/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mail;

/**
 *
 * @author ADMIN
 */
public class MailDAO extends DBContext{
    public List<Mail> getAllMail(){
        List<Mail> list= new ArrayList<>();
        String sql= "Select * from Mail";
        try{
            PreparedStatement st= connection.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                list.add(new Mail(rs.getInt("id"), 
                        rs.getString("name"),
                        rs.getString("from"),
                        rs.getString("password"),
                        rs.getString("subject"),
                        rs.getString("content")));
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return list;
    }
    public Mail getMailById(int id){
        String sql= "Select * from Mail where id = ?";
        try{
            PreparedStatement st= connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                return new Mail(rs.getInt("id"), 
                        rs.getString("name"),
                        rs.getString("from"),
                        rs.getString("password"),
                        rs.getString("subject"),
                        rs.getString("content"));
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    public static boolean isValidEmail(String email) {
   String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
   return email.matches(regex);
}
    public int EditEmail(Mail e ){
        String sql="Update Mail set [from] =?, password=?, subject=?, content=? where id=?";
        try{
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1,e.getFrom());
            st.setString(2, e.getPassword());
            st.setString(3, e.getSubject());
            st.setString(4, e.getContent());
            st.setInt(5, e.getId());
            return st.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return 0;
    }
    public static void main(String[] args){
        Mail e= new Mail(1,"Block","thutuoitd@gmail.com","Thuyeannguyen","NO","fghj");
        System.out.println( new MailDAO().isValidEmail("thutuoitd@gmail.com"));
    }
}
