/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author lethi
 */
public class NhapHangCT {
    private String IDNhapHang;
    private String IDNguyenLieu;
    private int soluong;

    public NhapHangCT() {
    }

    public NhapHangCT(String IDNhapHang, String IDNguyenLieu, int soluong) {
        this.IDNhapHang = IDNhapHang;
        this.IDNguyenLieu = IDNguyenLieu;
        this.soluong = soluong;
    }

    public String getIDNhapHang() {
        return IDNhapHang;
    }

    public void setIDNhapHang(String IDNhapHang) {
        this.IDNhapHang = IDNhapHang;
    }

    public String getIDNguyenLieu() {
        return IDNguyenLieu;
    }

    public void setIDNguyenLieu(String IDNguyenLieu) {
        this.IDNguyenLieu = IDNguyenLieu;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    
}
