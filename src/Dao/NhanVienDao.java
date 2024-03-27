/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Conn.ConnectionSQL;
import Model.NhanVien;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PhamNgocMinh
 */
public class NhanVienDao implements Dao<NhanVien> {

    @Override
    public ArrayList<NhanVien> GetArrayListAll() {
        ArrayList<NhanVien> NV = new ArrayList<>();
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            Statement st = conn.createStatement();
            String sql = "select * from NhanVien";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String manv = rs.getString("ID");
                String tenvn = rs.getString("Ten");
                Date ngaysinh = rs.getDate("Ngaysinh");
                String gioitinh = rs.getString("Gioitinh");
                String diachi = rs.getString("Diachi");
                String sdt = rs.getString("Sdt");
                String email = rs.getString("Email");
                BigDecimal Luong = rs.getBigDecimal("Luong");
                Date ngaytao = rs.getDate("NgayTao");
                Date ngayketthuc = rs.getDate("NgayKetThuc");
                String chucvu = rs.getString("ChucVu");
                NhanVien nv = new NhanVien(manv, tenvn, ngaysinh, gioitinh, diachi, sdt, email, chucvu, Luong, ngaytao, ngayketthuc);
                NV.add(nv);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NV;
    }

    public String CreateMaNV() {
        String ma = "";
        try {
            Connection conn = ConnectionSQL.getConnection();
            if (conn == null) {
                conn.close();
            } else {
                Statement st = conn.createStatement();
                String sql = "Select top 1 ID  from Nhanvien order by ID DESC";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    String x = rs.getString("ID").substring(2, rs.getString("ID").length());
                    ma = "NV" + String.format("%03d", Integer.parseInt(x) + 1);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ma;
    }
    
    /**
     * Check gmail có trong ds nhân viên không 
     * @return nhân viên: null hoặc not null
     */
    public NhanVien checkGmail(String gmail){
        NhanVien nv =null;
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            String sql = "select * from NhanVien where email = ?";
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, gmail);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                String manv = rs.getString("ID");
                String tenvn = rs.getString("Ten");
                Date ngaysinh = rs.getDate("Ngaysinh");
                String gioitinh = rs.getString("Gioitinh");
                String diachi = rs.getString("Diachi");
                String sdt = rs.getString("Sdt");
                String email = rs.getString("Email");
                BigDecimal Luong = rs.getBigDecimal("Luong");
                Date ngaytao = rs.getDate("NgayTao");
                Date ngayketthuc = rs.getDate("NgayKetThuc");
                String chucvu = rs.getString("ChucVu");
                nv = new NhanVien(manv, tenvn, ngaysinh, gioitinh, diachi, sdt, email, chucvu, Luong, ngaytao, ngayketthuc);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nv;
    }
    
    
    public String SumNV() {
        String tong = "";
        try {
            Connection conn = ConnectionSQL.getConnection();
            if (conn == null) {
                conn.close();
            } else {
                Statement st = conn.createStatement();
                String sql = "SELECT COUNT(*) FROM NhanVien";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    int soLuongNhanVien = rs.getInt(1);
                    tong = String.valueOf(soLuongNhanVien);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tong;
    }

    public NhanVien dangNhap(String username, String password, String congcu) {
        NhanVien nv =null;
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT nhanvien.* FROM Account join nhanvien on account.IDNhanVien = nhanvien.id "
                                                        + " WHERE Username = ? "
                                                        + "AND Passwords = ? "
                                                        + "AND chucvu like N'Quản lý' "
                                                        + "AND ngayketthuc is null");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String manv = rs.getString("ID");
                String tenvn = rs.getString("Ten");
                Date ngaysinh = rs.getDate("Ngaysinh");
                String gioitinh = rs.getString("Gioitinh");
                String diachi = rs.getString("Diachi");
                String sdt = rs.getString("Sdt");
                String email = rs.getString("Email");
                BigDecimal Luong = rs.getBigDecimal("Luong");
                Date ngaytao = rs.getDate("NgayTao");
                Date ngayketthuc = rs.getDate("NgayKetThuc");
                String chucvu = rs.getString("ChucVu");
                nv = new NhanVien(manv, tenvn, ngaysinh, gioitinh, diachi, sdt, email, chucvu, Luong, ngaytao, ngayketthuc);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
        
    }

    public boolean dangNhapBangEmail(String email, String password) {
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM NhanVien NV JOIN Account AC ON NV.ID = AC.IDNhanVien WHERE Email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String manv = rs.getString("ID");
                String storedPassword = rs.getString("Passwords");

                if (storedPassword != null && storedPassword.equals(password)) {
                    rs.close();
                    ps.close();
                    conn.close();
                    return true;
                }
            }
            rs.close();
            ps.close();
            conn.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<NhanVien> GetArrayListByID(String x) {
        ArrayList<NhanVien> NV = new ArrayList<>();
        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM NhanVien";
            if (x.equalsIgnoreCase("Còn làm việc")) {
                sql = "SELECT * FROM NhanVien WHERE NgayKetThuc IS NULL";
            } else if (x.equalsIgnoreCase("Đã Nghỉ Việc")) {
                sql = "SELECT * FROM NhanVien WHERE NgayKetThuc IS Not NULL";
            }
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String manv = rs.getString("ID");
                String tenvn = rs.getString("Ten");
                Date ngaysinh = rs.getDate("Ngaysinh");
                String gioitinh = rs.getString("Gioitinh");
                String diachi = rs.getString("Diachi");
                String sdt = rs.getString("Sdt");
                String email = rs.getString("Email");
                BigDecimal Luong = rs.getBigDecimal("Luong");
                Date ngaytao = rs.getDate("NgayTao");
                Date ngayketthuc = rs.getDate("NgayKetThuc");
                String chucvu = rs.getString("ChucVu");
                NhanVien nv = new NhanVien(manv, tenvn, ngaysinh, gioitinh, diachi, sdt, email, chucvu, Luong, ngaytao, ngayketthuc);
                NV.add(nv);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NV;
    }

    @Override
    public int UpdateSQL(NhanVien nv) {
        int check = 0;
        String sql = "UPDATE Nhanvien SET Ten = ?, Ngaysinh = ?, Gioitinh = ?, Diachi = ?, sdt = ?, Email = ?, ChucVu = ?, Luong = ? WHERE ID = ?";
        try {
            Connection cn = ConnectionSQL.getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, nv.getTen());
            ps.setString(2, sdf.format(nv.getNgaysinh()));
            ps.setString(3, nv.getGioitinh());
            ps.setString(4, nv.getDiachi());
            ps.setString(5, nv.getSdt());
            ps.setString(6, nv.getEmail());
            ps.setString(7, nv.getChucVu());
            ps.setBigDecimal(8, nv.getLuong());
            ps.setString(9, nv.getID());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public int InsertSQL(NhanVien nv) {
        int check = 0;
        String sql = "INSERT INTO Nhanvien (ID, Ten, Ngaysinh, Gioitinh, Diachi, sdt, Email, ChucVu, Luong, NgayTao,NgayKetThuc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, getdate(),null)";
        try {
            Connection cn = ConnectionSQL.getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, nv.getID());
            ps.setString(2, nv.getTen());
            ps.setString(3, sdf.format(nv.getNgaysinh()));
            ps.setString(4, nv.getGioitinh());
            ps.setString(5, nv.getDiachi());
            ps.setString(6, nv.getSdt());
            ps.setString(7, nv.getEmail());
            ps.setString(8, nv.getChucVu());
            ps.setBigDecimal(9, nv.getLuong());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public ArrayList<NhanVien> GetArrayListoderByDESC(String loaiSapXep, boolean tangGiam) {
        ArrayList<NhanVien> NV = new ArrayList<>();
        String tangGiams = "desc";
        if (tangGiam) {
            tangGiams = "asc";
        }
        try {
            System.out.println("da sap xep");
            Connection conn = new Conn.ConnectionSQL().getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM Nhanvien ORDER BY " + loaiSapXep + " " + tangGiams;
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String manv = rs.getString("ID");
                String tenvn = rs.getString("Ten");
                Date ngaysinh = rs.getDate("Ngaysinh");
                String gioitinh = rs.getString("Gioitinh");
                String diachi = rs.getString("Diachi");
                String sdt = rs.getString("Sdt");
                String email = rs.getString("Email");
                BigDecimal Luong = rs.getBigDecimal("Luong");
                Date ngaytao = rs.getDate("NgayTao");
                Date ngayketthuc = rs.getDate("NgayKetThuc");
                String chucvu = rs.getString("ChucVu");
                NhanVien nv = new NhanVien(manv, tenvn, ngaysinh, gioitinh, diachi, sdt, email, chucvu, Luong, ngaytao, ngayketthuc);
                NV.add(nv);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NV;
    }

    @Override
    public int DeleteSQL(String ID) {
        int check = 0;
        String sql = "UPDATE Nhanvien SET ngayketthuc = getdate() WHERE ID = ?";
        try {
            Connection cn = ConnectionSQL.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, ID);
            
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public ArrayList<NhanVien> GetArrayListByTenNV(String x) {
         ArrayList<NhanVien> NV = new ArrayList<>();
        try {
            Connection conn = new ConnectionSQL().getConnection();
            Statement st = conn.createStatement();
            String sql = "select * from Nhanvien where Ten like N'%" + x + "%'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String manv = rs.getString("ID");
                String tenvn = rs.getString("Ten");
                Date ngaysinh = rs.getDate("Ngaysinh");
                String gioitinh = rs.getString("Gioitinh");
                String diachi = rs.getString("Diachi");
                String sdt = rs.getString("Sdt");
                String email = rs.getString("Email");
                BigDecimal Luong = rs.getBigDecimal("Luong");
                Date ngaytao = rs.getDate("NgayTao");
                Date ngayketthuc = rs.getDate("NgayKetThuc");
                String chucvu = rs.getString("ChucVu");
                NhanVien nv = new NhanVien(manv, tenvn, ngaysinh, gioitinh, diachi, sdt, email, chucvu, Luong, ngaytao, ngayketthuc);
                NV.add(nv);
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return NV;
    }

    @Override
    public NhanVien getObjectById(String idObject) {
        NhanVien nhanVien = null;
        
        try {
            Connection conn = ConnectionSQL.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select * from nhanvien where ID like '"+idObject+"'");
            while (rs.next()){
                String ID =rs.getString("ID");
                String Ten = rs.getString("ten");
                java.sql.Date Ngaysinh = rs.getDate("Ngaysinh");
                String Gioitinh = rs.getString("Gioitinh");
                String Diachi = rs.getString("Diachi");
                String Sdt = rs.getString("Sdt");
                String  Email = rs.getString("Email");
                String  ChucVu = rs.getString("ChucVu");
                BigDecimal  Luong = rs.getBigDecimal("Luong");
                java.sql.Date  NgayTao = rs.getDate("NgayTao");
                java.sql.Date  NgayKetThuc = rs.getDate("NgayKetThuc");

                nhanVien=new NhanVien(ID, Ten, Ngaysinh, Gioitinh, Diachi, Sdt, Email, ChucVu, Luong, NgayTao, NgayKetThuc);
                
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return nhanVien;
    }

}
