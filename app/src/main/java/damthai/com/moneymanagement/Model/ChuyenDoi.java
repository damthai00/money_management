package damthai.com.moneymanagement.Model;

public class ChuyenDoi {
    private int machuyendoi;
    private long sotien;
    private long phi;
    private String loaiphi;      // taikhoanthe or tienmat
    private String hinhthuc;  // ruttien or chuyentien
    private String ngaychuyendoi;
    private String ghichu;
    private int mataikhoan;

    public ChuyenDoi(){

    }


    public ChuyenDoi(int machuyendoi, long sotien, long phi, String loaiphi, String hinhthuc,String ngaychuyendoi,String ghichu,int mataikhoan) {
        this.machuyendoi = machuyendoi;
        this.sotien = sotien;
        this.phi = phi;
        this.loaiphi = loaiphi;
        this.hinhthuc = hinhthuc;
        this.ngaychuyendoi = ngaychuyendoi;
        this.ghichu = ghichu;
        this.mataikhoan = mataikhoan;
    }

    public ChuyenDoi(long sotien, long phi,String loaiphi, String hinhthuc,String ngaychuyendoi, String ghichu, int mataikhoan) {
        this.sotien = sotien;
        this.phi = phi;
        this.loaiphi = loaiphi;
        this.hinhthuc = hinhthuc;
        this.ngaychuyendoi = ngaychuyendoi;
        this.ghichu = ghichu;
        this.mataikhoan = mataikhoan;
    }

    public int getMachuyendoi() {
        return machuyendoi;
    }

    public void setMachuyendoi(int machuyendoi) {
        this.machuyendoi = machuyendoi;
    }

    public long getSotien() {
        return sotien;
    }

    public void setSotien(long sotien) {
        this.sotien = sotien;
    }

    public long getPhi() {
        return phi;
    }

    public void setPhi(long phi) {
        this.phi = phi;
    }

    public String getHinhthuc() {
        return hinhthuc;
    }

    public void setHinhthuc(String hinhthuc) {
        this.hinhthuc = hinhthuc;
    }

    public String getLoaiphi() {
        return loaiphi;
    }

    public void setLoaiphi(String loaiphi) {
        this.loaiphi = loaiphi;
    }

    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }

    public String getNgaychuyendoi() {
        return ngaychuyendoi;
    }

    public void setNgaychuyendoi(String ngaychuyendoi) {
        this.ngaychuyendoi = ngaychuyendoi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
