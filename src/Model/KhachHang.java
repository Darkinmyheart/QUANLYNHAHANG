/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author PhamNgocMinh
 */
public class KhachHang implements Serializable{

    private String MaKH;
    private String TenKH;
    private String SDT;
    private String NgayTao;
    private String LastActive;
    private String MaNguoiTao;

    public static void main(String[] args) {
       // KhachHang kh1 = new KhachHang("001", "Minh", "", NgayTao, LastActive, MaNguoiTao);
    }
    public KhachHang() {
    }

    public KhachHang(String MaKH, String TenKH, String SDT, String NgayTao ,String LastActive,String MaNguoiTao) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.NgayTao = NgayTao;
        this.LastActive = LastActive;
        this.MaNguoiTao = MaNguoiTao;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getLastActive() {
        return LastActive;
    }

    public void setLastActive(String LastActive) {
        this.LastActive = LastActive;
    }

    public String getMaNguoiTao() {
        return MaNguoiTao;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "MaKH=" + MaKH + ", TenKH=" + TenKH + ", SDT=" + SDT + ", NgayTao=" + NgayTao + ", LastActive=" + LastActive + ", MaNguoiTao=" + MaNguoiTao + '}';
    }

    public void setMaNguoiTao(String MaNguoiTao) {
        this.MaNguoiTao = MaNguoiTao;
    }


    
    
}
