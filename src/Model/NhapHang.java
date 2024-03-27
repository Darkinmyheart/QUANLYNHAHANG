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
public class NhapHang {

    private String ID;
    private String IDNhaCungCap;
    private BigDecimal TongTien;
    private String NguoiGiaoHang;
    private String GhiChu;
    private Date NgayTao;
    private int IDNguoiNhan;

    public NhapHang(String ID, String IDNhaCungCap, BigDecimal TongTien, String NguoiGiaoHang, String GhiChu, Date NgayTao, int IDNguoiNhan) {
        this.ID = ID;
        this.IDNhaCungCap = IDNhaCungCap;
        this.TongTien = TongTien;
        this.NguoiGiaoHang = NguoiGiaoHang;
        this.GhiChu = GhiChu;
        this.NgayTao = NgayTao;
        this.IDNguoiNhan = IDNguoiNhan;
    }

    public NhapHang() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDNhaCungCap() {
        return IDNhaCungCap;
    }

    public void setIDNhaCungCap(String IDNhaCungCap) {
        this.IDNhaCungCap = IDNhaCungCap;
    }

    public BigDecimal getTongTien() {
        return TongTien;
    }

    public void setTongTien(BigDecimal TongTien) {
        this.TongTien = TongTien;
    }

    public String getNguoiGiaoHang() {
        return NguoiGiaoHang;
    }

    public void setNguoiGiaoHang(String NguoiGiaoHang) {
        this.NguoiGiaoHang = NguoiGiaoHang;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public int getIDNguoiNhan() {
        return IDNguoiNhan;
    }

    public void setIDNguoiNhan(int IDNguoiNhan) {
        this.IDNguoiNhan = IDNguoiNhan;
    }
    
    
    

}
