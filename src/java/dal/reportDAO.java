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
import model.Report;
import model.Room;

/**
 *
 * @author MY LAPTOP
 */
public class reportDAO extends DataConnection{
    public List<Report> getReport() {
        List<Report> list = new ArrayList<>();
        String sql =" select * from Report\n" +
                    "  Order by ReportID DESC";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    list.add(new Report(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5)));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        return list;
        }
    public Report getReportByID(int id){
        String sql="select * from Report \n" +
                   "  where ReportID=?";
        try{
            PreparedStatement st= connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                return new Report(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    public int response(String response,int reportId){
        String sql="UPDATE Report set Response=? \n" +
                   "  where ReportID=?";
        try{
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, response);
            st.setInt(2, reportId);
            return st.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
        return 0;    
    }
     public List<Report> getReportByUID(int uid) {
        List<Report> list = new ArrayList<>();
        String sql =" select * from Report\n" +
                    "  where UserReportId = ?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, uid);
                ResultSet rs = st.executeQuery();
                while(rs.next()){
                    list.add(new Report(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getString(5)));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        return list;
        }
    public void Report(String content,String purpose,int uid){
        String sql="insert into Report\n" +
                   "  values(?,?,?,null)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, content);
            st.setString(2, purpose);
            st.setInt(3, uid);  
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        reportDAO dao = new reportDAO();
//        List<Report> list = dao.getReport();
//        for (Report report : list) {
//            System.out.println(report);
//        }
        dao.response("OK", 1);
    }
}
