package damthai.com.moneymanagement.Model;

public class GiaoDich {
 private int magiaodich;
 private long sotien;
 private int nhom;
 private String ngaygiaodich;
 private String ghichu;
 private String hinhthucphi;
 private int mataikhoan;

    public GiaoDich(int magiaodich, long sotien, int nhom, String ngaygiaodich, String ghichu, String hinhthucphi, int mataikhoan) {
        this.magiaodich = magiaodich;
        this.sotien = sotien;
        this.nhom = nhom;
        this.ngaygiaodich = ngaygiaodich;
        this.ghichu = ghichu;
        this.hinhthucphi = hinhthucphi;
        this.mataikhoan = mataikhoan;
    }

    public GiaoDich(long sotien, int nhom, String ngaygiaodich, String ghichu, String hinhthucphi, int mataikhoan) {
        this.sotien = sotien;
        this.nhom = nhom;
        this.ngaygiaodich = ngaygiaodich;
        this.ghichu = ghichu;
        this.hinhthucphi = hinhthucphi;
        this.mataikhoan = mataikhoan;
    }
    public GiaoDich(){

    }

    public int getMagiaodich() {
        return magiaodich;
    }

    public void setMagiaodich(int magiaodich) {
        this.magiaodich = magiaodich;
    }

    public long getSotien() {
        return sotien;
    }

    public void setSotien(long sotien) {
        this.sotien = sotien;
    }

    public int getNhom() {
        return nhom;
    }

    public void setNhom(int nhom) {
        this.nhom = nhom;
    }

    public String getNgaygiaodich() {
        return ngaygiaodich;
    }

    public void setNgaygiaodich(String ngaygiaodich) {
        this.ngaygiaodich = ngaygiaodich;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getHinhthucphi() {
        return hinhthucphi;
    }

    public void setHinhthucphi(String hinhthucphi) {
        this.hinhthucphi = hinhthucphi;
    }

    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }
}
