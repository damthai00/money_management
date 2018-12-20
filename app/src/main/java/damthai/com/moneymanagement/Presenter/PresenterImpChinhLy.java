package damthai.com.moneymanagement.Presenter;

import android.widget.ListView;

import damthai.com.moneymanagement.Model.Nhom;
import damthai.com.moneymanagement.Model.TaiKhoan;

public interface PresenterImpChinhLy {
    TaiKhoan LoadDuLieu_TaiKhoan(TaiKhoan taiKhoan);
    ListView loadDuLiau_ListNhom(ListView listView,TaiKhoan taiKhoan);
    Nhom getNhom(int position,TaiKhoan taiKhoan);
}
