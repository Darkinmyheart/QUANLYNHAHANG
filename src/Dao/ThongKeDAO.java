/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThongKeDAO {

    public BigDecimal DoanhThuByLoaiTime(String loai) {
        BigDecimal total = BigDecimal.ZERO;
        try {
            Connection conn =  Conn.ConnectionSQL.getConnection();
            String sql = "";
            if (loai.equalsIgnoreCase("ngày")) {
                sql = "SELECT SUM(tongtien) AS DoanhThu FROM HoaDon WHERE ngaytao = CONVERT(DATE, GETDATE())";
            } else if (loai.equalsIgnoreCase("tháng")) {
                sql = "SELECT SUM(tongtien) AS DoanhThu FROM HoaDon WHERE Month(ngaytao) = month(getdate())";
            } else if (loai.equalsIgnoreCase("năm")) {
                sql = "SELECT SUM(tongtien) AS DoanhThu FROM HoaDon WHERE year(ngaytao) = year(getdate())";
            }

            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                total = rs.getBigDecimal("doanhthu");
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (total == null) {
            return BigDecimal.ZERO;
        } else {
            return total;
        }
    }

    public BigDecimal ChiPhiByLoaiTime(String loai) {
        BigDecimal total = new BigDecimal("0.00");

        try {
            Connection conn =  Conn.ConnectionSQL.getConnection();
            String sql = "";
            if (loai.equalsIgnoreCase("ngày")) {
                sql = "SELECT Sum(CAST(NhapHangCT.SoLuong as Decimal(15,2)) * NguyenLieu.Dongia) AS ChiPhi FROM NhapHangCT inner join NhapHang on NhapHang.ID = NhapHangCT.IDNhapHang inner join NguyenLieu on NguyenLieu.ID = NhapHangCT.IDNguyenLieu WHERE NhapHang.NgayTao = CONVERT(DATE, GETDATE())";
            } else if (loai.equalsIgnoreCase("tháng")) {
                sql = "SELECT Sum(CAST(NhapHangCT.SoLuong as Decimal(15,2)) * NguyenLieu.Dongia) AS ChiPhi FROM NhapHangCT inner join NhapHang on NhapHang.ID = NhapHangCT.IDNhapHang inner join NguyenLieu on NguyenLieu.ID = NhapHangCT.IDNguyenLieu WHERE Month(NhapHang.NgayTao) = month(getdate())";
            } else if (loai.equalsIgnoreCase("năm")) {
                sql = "SELECT Sum(CAST(NhapHangCT.SoLuong as Decimal(15,2)) * NguyenLieu.Dongia) AS ChiPhi FROM NhapHangCT inner join NhapHang on NhapHang.ID = NhapHangCT.IDNhapHang inner join NguyenLieu on NguyenLieu.ID = NhapHangCT.IDNguyenLieu WHERE year(NhapHang.NgayTao) = year(getdate())";
            }

            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                total = rs.getBigDecimal("ChiPhi");
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (total == null) {
            return BigDecimal.ZERO;
        } else {
            return total;
        }
    }

    public int SoHoaDonLoaiTime(String loai) {
        int total = 0;

        try {
            Connection conn =  Conn.ConnectionSQL.getConnection();
            String sql = "";
            if (loai.equalsIgnoreCase("ngày")) {
                sql = "SELECT count(id) AS dem FROM hoadon WHERE ngaytao = CONVERT(DATE, GETDATE())";
            } else if (loai.equalsIgnoreCase("tháng")) {
                sql = "SELECT count(id) AS dem FROM hoadon WHERE Month(ngaytao) = month(getdate())";
            } else if (loai.equalsIgnoreCase("năm")) {
                sql = "SELECT count(id) AS dem FROM hoadon WHERE year(ngaytao) = year(getdate())";
            }

            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt("dem");
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return total;
    }

    public int SoPhieuChiLoaiTime(String loai) {
        int total = 0;

        try {
            Connection conn =  Conn.ConnectionSQL.getConnection();
            String sql = "";
            if (loai.equalsIgnoreCase("ngày")) {
                sql = "SELECT count(ID) AS dem FROM NhapHang WHERE NgayTao = CONVERT(DATE, GETDATE())";
            } else if (loai.equalsIgnoreCase("tháng")) {
                sql = "SELECT count(ID) AS dem FROM NhapHang WHERE Month(NgayTao) = month(getdate())";
            } else if (loai.equalsIgnoreCase("năm")) {
                sql = "SELECT count(ID) AS dem FROM NhapHang WHERE year(NgayTao) = year(getdate())";
            }

            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt("dem");
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return total;
    }

}
