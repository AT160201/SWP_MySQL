/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class BlockDAO extends DBContext{
    public int BlockUser(int id, String block){
        String sql="Update Account set Block=? where userId=?";
        try{
            PreparedStatement st= connection.prepareStatement(sql);
            if(block.equalsIgnoreCase("block")){
                st.setInt(1,1);
            } else{
                st.setInt(1, 0);
            }
            st.setInt(2, id);
            return st.executeUpdate();
        }catch(SQLException ex){
            System.out.println();
        }
        return 0;
    }
    public static void main(String[] args){
        BlockDAO bdb= new BlockDAO();
        System.out.print(bdb.BlockUser(2, "block"));
    }
}
