package damthai.com.moneymanagement.Model;



public class TaiKhoan {
    private int mataikhoan,taikhoanthe,tienmat;
    private String tentaikhoan,matkhau;

    public TaiKhoan(int mataikhoan, String tentaikhoan, String matkhau,int taikhoanthe,int tienmat) {
        this.mataikhoan = mataikhoan;
        this.taikhoanthe = taikhoanthe;
        this.tienmat = tienmat;
        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
    }

    public TaiKhoan( String tentaikhoan, String matkhau,int taikhoanthe,int tienmat) {
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

    public int getTaikhoanthe() {
        return taikhoanthe;
    }

    public void setTaikhoanthe(int taikhoanthe) {
        this.taikhoanthe = taikhoanthe;
    }

    public int getTienmat() {
        return tienmat;
    }

    public void setTienmat(int tienmat) {
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
