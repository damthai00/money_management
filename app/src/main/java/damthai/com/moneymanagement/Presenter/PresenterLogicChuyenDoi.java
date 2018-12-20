package damthai.com.moneymanagement.Presenter;

import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.View.ViewChuyenDoi;
import damthai.com.moneymanagement.View.ViewIpmChuyenDoi;

public class PresenterLogicChuyenDoi implements PresenterImpChuyenDoi{

        ViewIpmChuyenDoi viewIpmChuyenDoi;
    public PresenterLogicChuyenDoi(ViewChuyenDoi viewChuyenDoi)
    {
        this.viewIpmChuyenDoi = viewChuyenDoi;
    }

    public PresenterLogicChuyenDoi()
    {
    }

    @Override
    public void XuLyChuyenDoi(TaiKhoan taiKhoan, RadioButton ruttien, RadioButton chuyentien, EditText sotien,
                              EditText phi, RadioButton taikhoanthe, RadioButton tienmat) {

        if(chuyentien.isChecked() ==false && ruttien.isChecked() == false)
            viewIpmChuyenDoi.ChuaChonHinhThucChuyenTien();
        else
            if (taikhoanthe.isChecked() == false && tienmat.isChecked()==false)
                    viewIpmChuyenDoi.ChuaChonHinhThucTraPhi();
            else
                if(sotien.getText().toString().equals("")) {
                    viewIpmChuyenDoi.ChuaNhapSoTienChuyenDoi();
                }else {
                    if(phi.getText().toString().equals(""))
                        phi.setText("0");
                    long sotien_taikhoanthe,sotien_tienmat,sotien_them,sotien_phi;
                    sotien_taikhoanthe = taiKhoan.getTaikhoanthe();
                    sotien_tienmat = taiKhoan.getTienmat();
                    sotien_them = Long.parseLong(String.valueOf(sotien.getText()));
                    sotien_phi = Long.parseLong(String.valueOf(phi.getText()));

                    if(ruttien.isChecked()==true && tienmat.isChecked()==true)
                    {
                        sotien_taikhoanthe = sotien_taikhoanthe - sotien_them;
                        sotien_tienmat = sotien_tienmat + sotien_them - sotien_phi;
                        viewIpmChuyenDoi.RutTienThanhCong();
                    }
                    else if (ruttien.isChecked() == true && taikhoanthe.isChecked() ==true)
                    {
                        sotien_taikhoanthe = sotien_taikhoanthe - sotien_them - sotien_phi;
                        sotien_tienmat = sotien_tienmat + sotien_them;
                        viewIpmChuyenDoi.RutTienThanhCong();
                    }
                    if(chuyentien.isChecked()==true && tienmat.isChecked() == true)
                    {
                        sotien_taikhoanthe = sotien_taikhoanthe + sotien_them;
                        sotien_tienmat = sotien_tienmat - sotien_them- sotien_phi;
                        viewIpmChuyenDoi.ChuyenTienThanhCong();
                    }else if(chuyentien.isChecked() == true && taikhoanthe.isChecked() == true)
                    {
                        sotien_taikhoanthe = sotien_taikhoanthe + sotien_them -sotien_phi;
                        sotien_tienmat = sotien_tienmat - sotien_them;
                        viewIpmChuyenDoi.ChuyenTienThanhCong();
                    }
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.setTaikhoan_using(sotien_taikhoanthe,sotien_tienmat);

                    taiKhoan.setTaikhoanthe(sotien_taikhoanthe);
                    taiKhoan.setTienmat(sotien_tienmat);
                    final DBManager dbManager = new DBManager(MainActivity.getInstance());
                    dbManager.updateTaikhoan(taiKhoan);
                }
    }
    public TaiKhoan LoadDuLieu(TaiKhoan taiKhoan)
    {
        final  DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<TaiKhoan> list = dbManager.getTaiKhoan();
        for (int i = 0;i<list.size();i++)
        {
           if( taiKhoan.getMataikhoan() == list.get(i).getMataikhoan())
           {
               taiKhoan = list.get(i);
           }
        }
                return taiKhoan;
    }
}
