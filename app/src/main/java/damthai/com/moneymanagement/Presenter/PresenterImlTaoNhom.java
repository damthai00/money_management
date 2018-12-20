package damthai.com.moneymanagement.Presenter;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import damthai.com.moneymanagement.Model.TaiKhoan;

public interface PresenterImlTaoNhom {
   void XuLyTaoNhom(EditText tennhom, RadioButton thunhap, RadioButton chitieu, TaiKhoan taiKhoan);
}
