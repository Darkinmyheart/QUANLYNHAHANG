/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author lethi
 */
public class HoaDon {
    private String ID;
    private BigDecimal TongTien;
    private Date NgayTao;
    private int IDNguoiTao;
    private String IDKhachHang;

    public HoaDon() {
    }

    public HoaDon(String ID, BigDecimal TongTien, Date NgayTao, int IDNguoiTao, String IDKhachHang) {
        this.ID = ID;
        this.TongTien = TongTien;
        this.NgayTao = NgayTao;
        this.IDNguoiTao = IDNguoiTao;
        this.IDKhachHang = IDKhachHang;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public BigDecimal getTongTien() {
        return TongTien;
    }

    public void setTongTien(BigDecimal TongTien) {
        this.TongTien = TongTien;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public int getIDNguoiTao() {
        return IDNguoiTao;
    }

    public void setIDNguoiTao(int IDNguoiTao) {
        this.IDNguoiTao = IDNguoiTao;
    }

    public String getIDKhachHang() {
        return IDKhachHang;
    }

    public void setIDKhachHang(String IDKhachHang) {
        this.IDKhachHang = IDKhachHang;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "ID=" + ID + ", TongTien=" + TongTien + ", NgayTao=" + NgayTao + ", IDNguoiTao=" + IDNguoiTao + ", IDKhachHang=" + IDKhachHang + '}';
    }
    
    
}
