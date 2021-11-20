/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ADMIN
 */
public class DataConnection {
    Connection connection;
    Statement st;
    public DataConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/onlinebooking", "root",
                    "123456");
        }catch(ClassNotFoundException ex){
            System.err.println(ex);
        }
        catch(SQLException e){
            System.err.println(e);
        }
        catch(Exception se){
            System.err.println();
        }
    }


    public static void main(String[] args) {
    }
}
