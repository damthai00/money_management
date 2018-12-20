package damthai.com.moneymanagement.Presenter;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.Nhom;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.View.ViewImpTaoNhom;

public class PresenterLogicTaoNhom implements PresenterImlTaoNhom {



    ViewImpTaoNhom viewImpTaoNhom;
    public PresenterLogicTaoNhom(ViewImpTaoNhom viewImpTaoNhom)
    {
        this.viewImpTaoNhom = viewImpTaoNhom;
    }

    @Override
    public void XuLyTaoNhom(EditText ten_nhom, RadioButton thunhap, RadioButton chitieu, TaiKhoan taiKhoan) {
        String tennhom = ten_nhom.getText().toString();
        if(tennhom.equals(""))
            viewImpTaoNhom.ChuaNhapTenNhom();
        else {
            if (thunhap.isChecked() == false && chitieu.isChecked() == false)
                viewImpTaoNhom.ChuaChonHinhThucThanhToan();
            else {
                final DBManager dbManager = new DBManager(MainActivity.getInstance());
                ArrayList<Nhom> list = dbManager.getNhom(taiKhoan.getMataikhoan());
                int dem = 0;
                for (int i = 0; i < list.size(); i++) {

                    if (tennhom.equals(list.get(i).getTennhom().toString()))
                    {
                        viewImpTaoNhom.NhomDaTonTai(tennhom);
                        dem = 1;
                        break;
                    }
                }
                if(dem ==0) {
                    int loai;
                    if(thunhap.isChecked() == true)
                        loai = 1;
                    else {
                        loai = 2;
                    }
                    Nhom nhom = new Nhom(tennhom,loai,taiKhoan.getMataikhoan());
                    dbManager.addNhom(nhom);
                    viewImpTaoNhom.TaoNhomThanhCong(tennhom);
                }
            }
        }
    }
}
