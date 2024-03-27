/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.HoaDon;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lethi
 */
public class HoaDonDao implements Dao<Model.HoaDon> {

    @Override
    public ArrayList<HoaDon> GetArrayListAll() {
        ArrayList<HoaDon> ds = new ArrayList<>();
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select * from hoadon");
            while (rs.next()) {
                ds.add(new HoaDon(rs.getString("ID"), rs.getBigDecimal("tongtien"), rs.getDate("ngaytao"), rs.getInt("IDNguoiTao"), rs.getString("IDKhachHang")));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ds;
    }

    @Override
    public ArrayList<HoaDon> GetArrayListoderByDESC(String kieusapxep, boolean tangGiam) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon getObjectById(String Id) {
        HoaDon hd = null;
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select * from hoadon where id like '"+Id+"'");
            while (rs.next()) {
                hd = new HoaDon(rs.getString("ID"), rs.getBigDecimal("tongtien"), rs.getDate("ngaytao"), rs.getInt("IDNguoiTao"), rs.getString("IDKhachHang"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hd;
    }

    @Override
    public int DeleteSQL(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int UpdateSQL(HoaDon ObObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int InsertSQL(HoaDon hoadon) {
        int check = 0;
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            PreparedStatement prep = conn.prepareStatement("insert into hoadon values (?,?,getdate(),?,?)");
            prep.setString(1, hoadon.getID());
            prep.setBigDecimal(2, hoadon.getTongTien());
            prep.setInt(3, hoadon.getIDNguoiTao());
            if (hoadon.getIDKhachHang().isEmpty()) {
                prep.setNull(4, Types.NVARCHAR);
            } else {
                prep.setString(4, hoadon.getIDKhachHang());
            }
            check = prep.executeUpdate();
            prep.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public ArrayList<HoaDon> getArrayListByTime(java.sql.Date From, java.sql.Date To) {
        ArrayList<HoaDon> ds = new ArrayList<>();
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            CallableStatement prep = conn.prepareCall("{CALL TimKiem_HoaDon_TheoNgay (?,?)}");
            prep.setDate(1, From);
            prep.setDate(2, To);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getString("ID"), rs.getBigDecimal("tongtien"), rs.getDate("ngaytao"), rs.getInt("IDNguoiTao"), rs.getString("IDKhachHang"));
                ds.add(hd);
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ds;
    }
    
}
