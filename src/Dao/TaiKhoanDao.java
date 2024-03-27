/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conn.ConnectionSQL;
import Model.NhanVien;
import Model.TaiKhoan;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhamNgocMinh
 */
public class TaiKhoanDao implements Dao<TaiKhoan> {

    public int UpdateSQL(String email, String newPass) {
        int CHECK = 0;
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Account SET Passwords = ? FROM Account JOIN NhanVien ON nhanvien.id = Account.IDNhanVien WHERE email = ?");
            ps.setString(1, newPass);
            ps.setString(2, email);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Mật khẩu đã được cập nhật thành công.");
                CHECK = 1;
            } else {
                System.out.println("Không tìm thấy email trong bảng NhânVien hoặc lỗi xảy ra khi cập nhật.");
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CHECK;
    }

    public NhanVien checkLogin(String tk, String mk) {
        NhanVien nv = null;
        try {
            Connection conn = ConnectionSQL.getConnection();
            PreparedStatement prep = conn.prepareStatement("select Nhanvien.* from Account "
                    + "join nhanvien on account.idnhanvien = nhanvien.id"
                    + " where username = ? and passwords=? and ngayketthuc is null and chucvu like 'Nhân viên'");
            prep.setString(1, tk);
            prep.setString(2, mk);

            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                String ID = rs.getString("ID");
                String Ten = rs.getString("ten");
                java.sql.Date Ngaysinh = rs.getDate("Ngaysinh");
                String Gioitinh = rs.getString("Gioitinh");
                String Diachi = rs.getString("Diachi");
                String Sdt = rs.getString("Sdt");
                String Email = rs.getString("Email");
                String ChucVu = rs.getString("ChucVu");
                BigDecimal Luong = rs.getBigDecimal("Luong");
                java.sql.Date NgayTao = rs.getDate("NgayTao");
                java.sql.Date NgayKetThuc = rs.getDate("NgayKetThuc");

                nv = new NhanVien(ID, Ten, Ngaysinh, Gioitinh, Diachi, Sdt, Email, ChucVu, Luong, NgayTao, NgayKetThuc);
            }
            prep.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nv;
    }

    public NhanVien checkLoginByEmail(String tk, String mk) {
        NhanVien nv = null;
        try {
            Connection conn = ConnectionSQL.getConnection();
            PreparedStatement prep = conn.prepareStatement("select Nhanvien.* from Account "
                    + "join nhanvien on account.idnhanvien = nhanvien.id"
                    + " where email = ? and passwords=? and ngayketthuc is null and chucvu like 'Nhân viên'");
            prep.setString(1, tk);
            prep.setString(2, mk);

            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                String ID = rs.getString("ID");
                String Ten = rs.getString("ten");
                java.sql.Date Ngaysinh = rs.getDate("Ngaysinh");
                String Gioitinh = rs.getString("Gioitinh");
                String Diachi = rs.getString("Diachi");
                String Sdt = rs.getString("Sdt");
                String Email = rs.getString("Email");
                String ChucVu = rs.getString("ChucVu");
                BigDecimal Luong = rs.getBigDecimal("Luong");
                java.sql.Date NgayTao = rs.getDate("NgayTao");
                java.sql.Date NgayKetThuc = rs.getDate("NgayKetThuc");

                nv = new NhanVien(ID, Ten, Ngaysinh, Gioitinh, Diachi, Sdt, Email, ChucVu, Luong, NgayTao, NgayKetThuc);
            }
            prep.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nv;
    }

    public TaiKhoan getTaiKhoanByIDUser(String idNhanvien) {

        TaiKhoan tk = null;
        try {
            Connection conn = ConnectionSQL.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select * from Account where IDNhanVien like '" + idNhanvien + "'");
            while (rs.next()) {
                tk = new TaiKhoan(rs.getInt("ID"), rs.getString("IDNhanVien"), rs.getString("Username"), rs.getString("Passwords"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tk;

    }

    @Override
    public ArrayList<TaiKhoan> GetArrayListAll() {
        ArrayList<TaiKhoan> tk = new ArrayList<>();
        try {
            Connection conn = ConnectionSQL.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select * from Account ");
            while (rs.next()) {
                tk.add (new TaiKhoan(rs.getInt("ID"), rs.getString("IDNhanVien"), rs.getString("Username"), rs.getString("Passwords")));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tk;
    }

    @Override
    public ArrayList<TaiKhoan> GetArrayListoderByDESC(String kieusapxep, boolean tangGiam) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TaiKhoan getObjectById(String Id) {
        TaiKhoan tk = null;
        try {
            Connection conn = ConnectionSQL.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select * from Account where ID like '" + Id + "'");
            while (rs.next()) {
                tk = new TaiKhoan(rs.getInt("ID"), rs.getString("IDNhanVien"), rs.getString("Username"), rs.getString("Passwords"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tk;
    }

    @Override
    public int DeleteSQL(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int UpdateSQL(TaiKhoan taikhoan) {
        int CHECK = 0;
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Account SET Passwords = ? FROM Account where idnhanvien ='" + taikhoan.getIdNhanVien() + "'");
            ps.setString(1, taikhoan.getPasswords());
            CHECK = ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CHECK;
    }

    @Override
    public int InsertSQL(TaiKhoan tk) {
        int check = 0;

        try {
            Connection conn = ConnectionSQL.getConnection();
            PreparedStatement prep = conn.prepareStatement("insert into account values(?,?,'123')");
            prep.setString(1, tk.getIdNhanVien());
            prep.setString(2, tk.getUsername());
            check = prep.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

}
