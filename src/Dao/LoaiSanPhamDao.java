/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.LoaiSanPham;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author PhamNgocMinh
 */
public class LoaiSanPhamDao implements Dao<Model.LoaiSanPham>{

    @Override
    public ArrayList<LoaiSanPham> GetArrayListAll() {
        ArrayList<LoaiSanPham> ds = new ArrayList<>();
        try {
            Connection conn = Conn.ConnectionSQL.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("select * from loaisp");
            while (rs.next()){
                String ID = rs.getString("ID");
                String TEN = rs.getString("TEN");
                Date NgayTao = rs.getDate("NgayTao");
                Date NgayKetThuc = rs.getDate("NgayKetThuc");
                ds.add(new LoaiSanPham(ID, TEN, NgayTao, NgayKetThuc));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ds;
        }

    @Override
    public ArrayList<LoaiSanPham> GetArrayListoderByDESC(String kieusapxep, boolean tangGiam) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LoaiSanPham getObjectById(String Id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int DeleteSQL(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int UpdateSQL(LoaiSanPham ObObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int InsertSQL(LoaiSanPham Object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
