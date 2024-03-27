/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lethi
 */
public class CongThucDAO {

    public int SoluongSanPhamTheoNguyenLieu(String idsanpham) {
        int soluong = 0;

        try {
            Connection conn = new Conn.ConnectionSQL().getConnection();
            CallableStatement prep = conn.prepareCall("{CALL soluong_sanpham(?)}");
            prep.setString(1, idsanpham);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                soluong = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CongThucDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return soluong;
    }
}
