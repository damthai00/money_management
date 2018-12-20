package damthai.com.moneymanagement.Presenter;

import android.widget.ListView;

import damthai.com.moneymanagement.Model.GiaoDich;
import damthai.com.moneymanagement.Model.TaiKhoan;

public interface PresenterImpThem {
    ListView LoadDSNhom(ListView listView, TaiKhoan taiKhoan);

    void ThemGiaoDich(GiaoDich giaoDich);
}
