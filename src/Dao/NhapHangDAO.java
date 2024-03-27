/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.NhapHang;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lethi
 */
public class NhapHangDAO implements Dao<Model.NhapHang> {

    @Override
    public ArrayList<NhapHang> GetArrayListAll() {
        ArrayList<NhapHang> danhSachNhapHang = new ArrayList<>();

        try (Connection connection = Conn.ConnectionSQL.getConnection()) {
            String query = "SELECT * FROM NhapHang";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                NhapHang nhapHang = new NhapHang();
                nhapHang.setID(resultSet.getString("ID"));
                nhapHang.setIDNhaCungCap(resultSet.getString("IDNhaCungCap"));
                nhapHang.setNguoiGiaoHang(resultSet.getString("NguoiGiaoHang"));
                nhapHang.setGhiChu(resultSet.getString("GhiChu"));
                nhapHang.setNgayTao(resultSet.getDate("NgayTao"));
                nhapHang.setIDNguoiNhan(resultSet.getInt("IDNguoiNhan"));

                danhSachNhapHang.add(nhapHang);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachNhapHang;
    }

    @Override
    public ArrayList<NhapHang> GetArrayListoderByDESC(String kieusapxep, boolean tangGiam) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public NhapHang getObjectById(String Id) {
        NhapHang nhapHang = null;

        try (Connection connection = Conn.ConnectionSQL.getConnection()) {
            String query = "SELECT * FROM NhapHang where id like '"+Id+"'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                nhapHang = new NhapHang();
                nhapHang.setID(resultSet.getString("ID"));
                nhapHang.setIDNhaCungCap(resultSet.getString("IDNhaCungCap"));
                nhapHang.setNguoiGiaoHang(resultSet.getString("NguoiGiaoHang"));
                nhapHang.setGhiChu(resultSet.getString("GhiChu"));
                nhapHang.setNgayTao(resultSet.getDate("NgayTao"));
                nhapHang.setIDNguoiNhan(resultSet.getInt("IDNguoiNhan"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhapHang;
    }

    @Override
    public int DeleteSQL(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int UpdateSQL(NhapHang ObObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int InsertSQL(NhapHang nh) {
        int check = 0;
        
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            PreparedStatement prep = conn.prepareStatement("Insert into NhapHang values (?,?,?,?,getdate(),?)");
            prep.setString(1, nh.getID());
            prep.setString(2, nh.getIDNhaCungCap());
            prep.setString(3, nh.getNguoiGiaoHang());
            prep.setString(4, nh.getGhiChu());
            prep.setInt(5, nh.getIDNguoiNhan());
            check = prep.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NhapHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return check;
    }
    
    
}
