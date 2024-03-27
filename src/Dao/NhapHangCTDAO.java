/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import java.sql.*;
import java.util.ArrayList;
import Model.NhapHangCT;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author lethi
 */
public class NhapHangCTDAO {
    public ArrayList<NhapHangCT> getArrayListByIDNhaphang(String id){
        ArrayList<NhapHangCT> arr = new ArrayList<>();
        
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select * from NhapHangCT where IDNhapHang like '"+id+"'");
            while(rs.next()){
                arr.add(new NhapHangCT(rs.getString("IDNhapHang"), rs.getString("IDNguyenLieu"), rs.getInt("soluong")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhapHangCTDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return arr;
    }
    public int InsertSQLArraList(ArrayList<NhapHangCT> ds){
        int check = 0;
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            CallableStatement prep = conn.prepareCall("{CALL SP_insert_NhapHangCT (?,?,?)}");
            for (NhapHangCT d : ds) {
                prep.setString(1, d.getIDNhapHang());
                prep.setString(2, d.getIDNguyenLieu());
                prep.setInt(3, d.getSoluong());
                prep.addBatch();
            }
            prep.executeBatch();
            check = 1;
        } catch (SQLException ex) {
            Logger.getLogger(NhapHangCTDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return check;
    }
}
