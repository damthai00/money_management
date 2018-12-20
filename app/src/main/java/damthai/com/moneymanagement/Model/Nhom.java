package damthai.com.moneymanagement.Model;

public class Nhom {
    private int manhom;
    private String tennhom;
    private int loai;
    private int mataikhoan;

    public Nhom(int manhom, String tennhom, int loai,int mataikhoan) {
        this.manhom = manhom;
        this.tennhom = tennhom;
        this.loai = loai;
        this.mataikhoan = mataikhoan;
    }

    public Nhom(String tennhom, int loai,int mataikhoan) {
        this.tennhom = tennhom;
        this.loai = loai;
        this.mataikhoan = mataikhoan;
    }

    public Nhom(){
    }
    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }

    public int getManhom() {
        return manhom;
    }

    public void setManhom(int manhom) {
        this.manhom = manhom;
    }

    public String getTennhom() {
        return tennhom;
    }

    public void setTennhom(String tennhom) {
        this.tennhom = tennhom;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }
}
