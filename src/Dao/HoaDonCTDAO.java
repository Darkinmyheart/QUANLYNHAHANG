/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.HoaDonCT;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lethi
 */
public class HoaDonCTDAO implements Dao<Model.HoaDonCT> {

    public ArrayList<HoaDonCT> GetArrayListByIDHoaDon(String id) {
        ArrayList<HoaDonCT> dsIdHoaDon = new ArrayList<>();
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("Select * from hoadonct where idhoadon like '" + id + "'");
            while (rs.next()) {
                dsIdHoaDon.add(new HoaDonCT(rs.getString("IDHoaDon"), rs.getString("IDSanPham"), rs.getInt("soluong"), ""));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonCTDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsIdHoaDon;
    }

    @Override
    public ArrayList<HoaDonCT> GetArrayListAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<HoaDonCT> GetArrayListoderByDESC(String kieusapxep, boolean tangGiam) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonCT getObjectById(String Id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int DeleteSQL(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int UpdateSQL(HoaDonCT ObObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void InsertSQL(ArrayList<HoaDonCT> dsHDCT) {
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            CallableStatement prep = conn.prepareCall("{CALL SP_insert_HoaDonCT(?, ?, ?)}");
            for (HoaDonCT hdct : dsHDCT) {

                prep.setString(1, hdct.getIDHoaDon());
                prep.setString(2, hdct.getIDSanPham());
                prep.setInt(3, hdct.getSoluong());
                prep.addBatch();

            }
            prep.executeBatch();
            prep.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonCTDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int InsertSQL(HoaDonCT Object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
