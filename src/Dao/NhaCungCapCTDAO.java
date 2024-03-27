/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.NhaCungCapCT;
import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhamNgocMinh
 */
public class NhaCungCapCTDAO {

    public Map<String[],NhaCungCapCT> GetArrayListAll() {
        Map<String[],NhaCungCapCT> map = new HashMap<>();
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select * from nhacungcapct");
            while(rs.next()){
                map.put(new String[]{rs.getString("IDNhaCungCap"),rs.getString("IDNguyenLieu")}, new NhaCungCapCT(rs.getString("IDNhaCungCap"), rs.getString("IDNguyenLieu"), rs.getString("GhiChu"), rs.getDate("Ngaytao")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapCTDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
        
    }

    public ArrayList<NhaCungCapCT> GetArrayListoderByDESC(String kieusapxep, boolean tangGiam) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public NhaCungCapCT getObjectById(String Id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int DeleteSQL(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int UpdateSQL(NhaCungCapCT ObObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int InsertSQL(NhaCungCapCT Object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
//    public ArrayList<NhaCungCapCT> GetArrayListByIDNCC(String idNCC){
//        ArrayList<NhaCungCapCT> arr = null;
//        try {
//            Connection conn = new Conn.ConnectionSQL().getConnection();
//            ResultSet rs = conn.createStatement().executeQuery("select * from nhacungcapct where IDNhaCungCap = '"+idNCC+"'");
//            while(rs.next()){
////                new NhaCungCapCT(idNCC, idNCC, idNCC, NgayTao)
//                NhaCungCapCT nccct = new NhaCungCapCT(rs.getString("idNCC"), idNCC, idNCC, NgayTao)
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(NhaCungCapCTDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return arr;
//    }
}
