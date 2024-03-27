/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conn.ConnectionSQL;
import Model.KhachHang;
import java.sql.*;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhamNgocMinh
 */
public class KhachHangDao implements Dao<KhachHang> {

    @Override
    public ArrayList<KhachHang> GetArrayListAll() {
        ArrayList<KhachHang> KH = new ArrayList<>();
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            Statement st = conn.createStatement();
            String sql = "select * from KhachHang";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                KH.add(new KhachHang(rs.getString("ID"),
                        rs.getString("TEN"),
                        rs.getString("SDT"),
                        rs.getString("Ngaytao"),
                        rs.getString("LastActive"),
                        rs.getString("IDNguoiTao")
                ));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return KH;
    }

    public ArrayList<KhachHang> GetArrayListByTen(String x, String loai) {
        ArrayList<KhachHang> KH = new ArrayList<>();
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            Statement st = conn.createStatement();
            String sql = "select * from KhachHang where " + loai + " like N'%" + x + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                KH.add(new KhachHang(rs.getString("ID"),
                        rs.getString("TEN"),
                        rs.getString("SDT"),
                        rs.getString("Ngaytao"),
                        rs.getString("LastActive"),
                        rs.getString("IDNguoiTao")));
            };
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return KH;
    }

    public int checkDup(String sdt) {
        int check = 0;
        try {

            Connection conn = new Conn.ConnectionSQL().getConnection();
            Statement st = conn.createStatement();
            String sql = "select * from KhachHang where sdt like '" + sdt + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                check = 1;
            }
            System.out.println(check);
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public String CreateKH() {
        String ma = "";
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            if (conn == null) {
                conn.close();
            } else {
                Statement st = conn.createStatement();
                String sql = "Select top 1 ID from KhachHang order by ID DESC ";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    String x = rs.getString("ID").substring(2, rs.getString("ID").length());
                    ma = "KH" + String.format("%03d", Integer.parseInt(x) + 1);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ma;
    }

    public int DeleteSQL() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int InsertSQL() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int InsertSQL(KhachHang KH) {
        int check = 0;
        String sql = "INSERT INTO KhachHang (ID, TEN, SDT, Ngaytao, LastActive, IDNguoiTao) VALUES (?, ?, ?,GETDATE(), null,?)";
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, KH.getMaKH());
            ps.setString(2, KH.getTenKH());
            ps.setString(3, KH.getSDT());

            ps.setString(4, KH.getMaNguoiTao());
            check = ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public int DeleteSQL(String x) {
        int check = 0;
        ConnectionSQL conn = new ConnectionSQL();
        try {
            if (conn.getConnection() == null) {
                conn.getConnection().close();
            } else {
                String sql = "UPDATE KhachHang SET LastActive = GetDATE() WHERE ID = ? ";
                conn.getConnection().createStatement();
                PreparedStatement ps = conn.getConnection().prepareStatement(sql);
                ps.setString(1, x);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public int UpdateSQL(KhachHang KH) {
        int check = 0;
        String sql = "UPDATE KhachHang SET TEN = ?, SDT = ?, IDNguoiTao = ? WHERE ID = ?";
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, KH.getTenKH());
            ps.setString(2, KH.getSDT());
            ps.setString(3, KH.getMaNguoiTao());
            ps.setString(4, KH.getMaKH());
            check = ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public KhachHang getObjectById(String Id) {
        KhachHang KH = new KhachHang();
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            Statement st = conn.createStatement();
            String sql = "select * from KhachHang";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                KH = new KhachHang(rs.getString("ID"),
                        rs.getString("TEN"),
                        rs.getString("SDT"),
                        rs.getString("Ngaytao"),
                        rs.getString("LastActive"),
                        rs.getString("IDNguoiTao")
                );
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return KH;
    }

    @Override
    public ArrayList<KhachHang> GetArrayListoderByDESC(String kieusapxep, boolean tangGiam) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
