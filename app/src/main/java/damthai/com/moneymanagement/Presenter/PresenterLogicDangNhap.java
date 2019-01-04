package damthai.com.moneymanagement.Presenter;

import java.util.ArrayList;

import damthai.com.moneymanagement.ImpDangNhapActivity;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.View.ViewImpDangNhap;

public class PresenterLogicDangNhap implements PresenterImpDangNhap {


    ViewImpDangNhap viewImpDangNhap;
    public PresenterLogicDangNhap(ViewImpDangNhap viewImpDangNhap){
        this.viewImpDangNhap = viewImpDangNhap;
    }

    public PresenterLogicDangNhap() {

    }

    @Override
    public void XuLyDangNhap(String user, String pass) {
        final DBManager dbManager = new DBManager(ImpDangNhapActivity.getInstance());
        ArrayList<TaiKhoan> list = dbManager.getTaiKhoan();

        int dem = 0;
        for (int i=0;i<list.size();i++)
        {
            if(user.equals(list.get(i).getTentaikhoan())) {
                if (pass.equals(list.get(i).getMatkhau()))
                {
                    viewImpDangNhap.DangNhapThanhCong(list.get(i));
                    dem =1;
                    break;
                }
            }
        }
        if(dem ==0)
            viewImpDangNhap.DangNhapThatBai();
    }


    @Override
    public void XuLyDangKi(String user, String pass){
        //lấy dữ liệu từ Model
        final DBManager dbManager = new DBManager(ImpDangNhapActivity.getInstance());
        ArrayList<TaiKhoan> list = dbManager.getTaiKhoan();

        //kiểm tra điều kiện đăng kí  (tài khoản trống hoặc trùng)
        if (user.equals("") || pass.equals(""))
            viewImpDangNhap.DangKiThatBai();
        else {
            int dem1 = 0;
            for(int i =0;i<user.length();i++)
                if(user.charAt(i) == ' '){
                dem1 =1;
                viewImpDangNhap.TenDangNhapCoKhoangTrang();
                break;
                }
            for(int i =0;i<pass.length();i++)
                if(pass.charAt(i) == ' '){
                    dem1 =1;
                    viewImpDangNhap.MatKhauCoKhoangTrang();
                    break;
                }

                if(dem1 == 0) {
                int dem = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (user.equals(list.get(i).getTentaikhoan())) {
                        viewImpDangNhap.TaiKhoanDaTonTai();
                        dem = 1;
                        break;
                    }
                }
                if (dem == 0) {
                    TaiKhoan taiKhoan = new TaiKhoan();
                    taiKhoan.setTentaikhoan(user);
                    taiKhoan.setMatkhau(pass);
                    dbManager.addTaiKhoan(taiKhoan);
                    viewImpDangNhap.DangKiThanhCong();
                }
            }
        }
    }
}
