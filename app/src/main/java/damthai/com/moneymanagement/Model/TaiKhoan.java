package damthai.com.moneymanagement.Model;



public class TaiKhoan {
    private long taikhoanthe,tienmat;
    private int mataikhoan;
    private String tentaikhoan,matkhau;

    public TaiKhoan(int mataikhoan, String tentaikhoan, String matkhau,long taikhoanthe,long tienmat) {
        this.mataikhoan = mataikhoan;
        this.taikhoanthe = taikhoanthe;
        this.tienmat = tienmat;
        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
    }

    public TaiKhoan( String tentaikhoan, String matkhau,long taikhoanthe,long tienmat) {
        this.taikhoanthe = taikhoanthe;
        this.tienmat = tienmat;
        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
    }

    public TaiKhoan(){
    }


    public int getMataikhoan() {
        return mataikhoan;
    }

    public void setMataikhoan(int mataikhoan) {
        this.mataikhoan = mataikhoan;
    }

    public long getTaikhoanthe() {
        return taikhoanthe;
    }

    public void setTaikhoanthe(long taikhoanthe) {
        this.taikhoanthe = taikhoanthe;
    }

    public long getTienmat() {
        return tienmat;
    }

    public void setTienmat(long tienmat) {
        this.tienmat = tienmat;
    }

    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
