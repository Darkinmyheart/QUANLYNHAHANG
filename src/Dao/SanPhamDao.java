/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conn.ConnectionSQL;
import Model.SanPham;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhamNgocMinh
 */
public class SanPhamDao implements Dao<SanPham> {

    @Override
    public ArrayList<SanPham> GetArrayListAll() {
        ArrayList<SanPham> ListSanPham = new ArrayList();
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            Statement st = conn.createStatement();
            String sql = "select * from SanPham";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String Masp = rs.getString("ID");
                String tensp = rs.getString("Ten");
                String Mota = rs.getString("MoTa");
                String DVT = rs.getString("Donvitinh");
                BigDecimal DonGia = rs.getBigDecimal("Dongia");
                Date ngaytao = rs.getDate("NgayTao");
                Date ngayketthuc = rs.getDate("NgayKetThuc");
                String MaLoaisp = rs.getString("IDloai");
                SanPham sp = new SanPham(Masp, tensp, Mota, DVT, DonGia, ngaytao, ngayketthuc, MaLoaisp);
                ListSanPham.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListSanPham;
    }

    public ArrayList<SanPham> getArrayListByLoaiSP(String x) {
        ArrayList<SanPham> ListSanPham = new ArrayList();
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            Statement st = conn.createStatement();
            String sql = "select * from SanPham where IDloai like '%" + x + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String Masp = rs.getString("ID");
                String tensp = rs.getString("Ten");
                String Mota = rs.getString("MoTa");
                String DVT = rs.getString("Donvitinh");
                BigDecimal DonGia = rs.getBigDecimal("Dongia");
                Date ngaytao = rs.getDate("NgayTao");
                Date ngayketthuc = rs.getDate("NgayKetThuc");
                String MaLoaisp = rs.getString("IDloai");
                SanPham sp = new SanPham(Masp, tensp, Mota, DVT, DonGia, ngaytao, ngayketthuc, MaLoaisp);
                ListSanPham.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListSanPham;
    }

    @Override
    public ArrayList<SanPham> GetArrayListoderByDESC(String kieusapxep, boolean tangGiam) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPham getObjectById(String Id) {
        SanPham sanpham = new SanPham();
        try {
            Connection conn = ConnectionSQL.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select * from sanpham where ID = '" + Id + "'");
            while (rs.next()) {
                String ID = rs.getString("ID");
                String Ten = rs.getString("ten");
                String mota = rs.getString("Mota");
                String Donvitinh = rs.getString("Donvitinh");
                BigDecimal Dongia = rs.getBigDecimal("Dongia");
                java.sql.Date Ngaytao = rs.getDate("Ngaytao");
                java.sql.Date Ngayketthuc = rs.getDate("Ngayketthuc");
                String IDloai = rs.getString("IDloai");
                sanpham = new SanPham(ID, Ten, mota, Donvitinh, Dongia, Ngaytao, Ngayketthuc, IDloai);
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return sanpham;
    }

    @Override
    public int DeleteSQL(String ID) {
        int check = 0;
        ConnectionSQL conn = new ConnectionSQL();
        try {
            if (conn.getConnection() == null) {
                conn.getConnection().close();
            } else {
                String sql = "UPDATE SanPham SET NgayKetThuc = GetDATE() WHERE ID = ? ";
                conn.getConnection().createStatement();
                PreparedStatement ps = conn.getConnection().prepareStatement(sql);
                ps.setString(1, ID);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public int UpdateSQL(SanPham ObObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int InsertSQL(SanPham Object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
