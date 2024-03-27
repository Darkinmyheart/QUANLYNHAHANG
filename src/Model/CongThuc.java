/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author lethi
 */
public class CongThuc {
    private String IDNguyenLieu;
    private String IDSanPham;
    private BigDecimal soluong;
    public static void main(String[] args) {
        CongThuc ct = new CongThuc("111t","111sp",BigDecimal.valueOf(100));
        CongThuc ct1 = new CongThuc("111t","111sp123",BigDecimal.valueOf(100));

        System.out.println(ct.equals(ct1));
    }
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }


    public boolean equals(CongThuc obj) {
        if (this.getIDNguyenLieu() == obj.getIDNguyenLieu()) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CongThuc other = (CongThuc) obj;
        if (!Objects.equals(this.IDNguyenLieu, other.IDNguyenLieu)) {
            return false;
        }
        if (!Objects.equals(this.IDSanPham, other.IDSanPham)) {
            return false;
        }
        return Objects.equals(this.soluong, other.soluong);
    }

    public CongThuc(String IDNguyenLieu, String IDSanPham, BigDecimal soluong) {
        this.IDNguyenLieu = IDNguyenLieu;
        this.IDSanPham = IDSanPham;
        this.soluong = soluong;
    }

    public CongThuc() {
    }

    public String getIDNguyenLieu() {
        return IDNguyenLieu;
    }

    public void setIDNguyenLieu(String IDNguyenLieu) {
        this.IDNguyenLieu = IDNguyenLieu;
    }

    public String getIDSanPham() {
        return IDSanPham;
    }

    public void setIDSanPham(String IDSanPham) {
        this.IDSanPham = IDSanPham;
    }

    public BigDecimal getSoluong() {
        return soluong;
    }

    public void setSoluong(BigDecimal soluong) {
        this.soluong = soluong;
    }
    
    
    
}
