package damthai.com.moneymanagement.Presenter;

import java.util.jar.Manifest;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.View.ViewImpThayDoiSoDu;

public class PresenterLogicThayDoiSoDu implements PresenterImpThayDoiSoDu {

    ViewImpThayDoiSoDu viewImpThayDoiSoDu;
    public PresenterLogicThayDoiSoDu(ViewImpThayDoiSoDu viewImpThayDoiSoDu)
    {
        this.viewImpThayDoiSoDu = viewImpThayDoiSoDu;
    }


    @Override
    public void XuLyThayDoiSoDu_TaiKhoanThe(TaiKhoan taiKhoan,String soduthaydoi) {
        if (soduthaydoi.equals(""))
            viewImpThayDoiSoDu.ChuaNhapGiaTriThayDoi();
        else {
            taiKhoan.setTaikhoanthe(Integer.parseInt(soduthaydoi));
            final DBManager dbManager = new DBManager(MainActivity.getInstance());
            dbManager.updateTaikhoan(taiKhoan);
            viewImpThayDoiSoDu.ThayDoiThanhCong();
        }
    }

    @Override
    public void XuLyThayDoiSoDu_TienMat(TaiKhoan taiKhoan,String soduthaydoi) {
        if(soduthaydoi.equals(""))
            viewImpThayDoiSoDu.ChuaNhapGiaTriThayDoi();
        else {
            taiKhoan.setTienmat(Integer.parseInt(soduthaydoi));
            final DBManager dbManager = new DBManager(MainActivity.getInstance());
            dbManager.updateTaikhoan(taiKhoan);
            viewImpThayDoiSoDu.ThayDoiThanhCong();
        }
    }
}
