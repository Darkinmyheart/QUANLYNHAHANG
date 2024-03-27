/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author lethi
 */
public class HoaDonCT implements Serializable{
    private String IDHoaDon;
    private String IDSanPham;
    private int Soluong;
    private String IDKhuyenMai;

    public HoaDonCT() {
    }

    @Override
    public String toString() {
        return "HoaDonCT{" + "IDHoaDon=" + IDHoaDon + ", IDSanPham=" + IDSanPham + ", Soluong=" + Soluong + ", IDKhuyenMai=" + IDKhuyenMai + '}';
    }

    public HoaDonCT(String IDHoaDon, String IDSanPham, int Soluong, String IDKhuyenMai) {
        this.IDHoaDon = IDHoaDon;
        this.IDSanPham = IDSanPham;
        this.Soluong = Soluong;
        this.IDKhuyenMai = IDKhuyenMai;
    }

    public String getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(String IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public String getIDKhuyenMai() {
        return IDKhuyenMai;
    }

    public void setIDKhuyenMai(String IDKhuyenMai) {
        this.IDKhuyenMai = IDKhuyenMai;
    }
    
}
