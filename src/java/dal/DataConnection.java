/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DataConnection {

    public static Connection getConnection() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
 
        String url = "jdbc:mysql://localhost:3306/OnlineBooking";
        String user = "root";
        String password = "123456";
 
        try {
            con = (Connection) DriverManager.getConnection(url, user, password);
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

 
        } catch (SQLException ex) {
            System.out.print(ex);
 
        }
        return (con);
    }


    public static void main(String[] args) {
        Connection c = getConnection();
        if (c == null) {
            System.out.println("something wrong");
        } else {
            System.out.println("ok");
        }
    }
}
