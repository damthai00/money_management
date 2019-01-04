package damthai.com.moneymanagement.Presenter;

import android.widget.EditText;
import android.widget.RadioButton;

import damthai.com.moneymanagement.Model.ChuyenDoi;
import damthai.com.moneymanagement.Model.TaiKhoan;



public interface PresenterImpChuyenDoi {
    public void XuLyChuyenDoi(TaiKhoan taiKhoan, RadioButton ruttien, RadioButton chuyentien, EditText sotien,
                              EditText phi, RadioButton tienmat, RadioButton Taikhoanthe,EditText ngaychuyendoi,EditText ghichu);

    TaiKhoan LoadDuLieu(TaiKhoan taiKhoan);
    void SuaChuyenDoi(ChuyenDoi chuyenDoi);
    void XoaChuyenDoi(ChuyenDoi chuyenDoi);
}
