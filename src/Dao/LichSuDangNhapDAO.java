/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.LichSuDangNhap;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lethi
 */
public class LichSuDangNhapDAO implements Dao<Model.LichSuDangNhap>{

    @Override
    public ArrayList<LichSuDangNhap> GetArrayListAll() {
        ArrayList<LichSuDangNhap> arr = null;
        
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select * from lichsudangnhap");
            while(rs.next()){
                LichSuDangNhap ls = new LichSuDangNhap(rs.getInt("ID"), rs.getInt("IDAccount"), rs.getDate("timelogin"), rs.getDate("timelogout"));
                arr.add(ls);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return arr;
    }

    @Override
    public ArrayList<LichSuDangNhap> GetArrayListoderByDESC(String kieusapxep, boolean tangGiam) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LichSuDangNhap getObjectById(String Id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int DeleteSQL(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int UpdateSQL(LichSuDangNhap lichSuDangNhap) {
        int check = 0;
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            PreparedStatement prep = conn.prepareStatement("update lichsudangnhap set timelogout = getdate() where idaccount = ?");
            prep.setInt(1, lichSuDangNhap.getIDAccount());
            check = prep.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    @Override
    public int InsertSQL(LichSuDangNhap lichSuDangNhap) {
        int id = 0;
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            CallableStatement prep = conn.prepareCall("{CALL get_ID_LichSuDangNhap (?)}");
            prep.setInt(1, lichSuDangNhap.getIDAccount());
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {                
                rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LichSuDangNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
}
