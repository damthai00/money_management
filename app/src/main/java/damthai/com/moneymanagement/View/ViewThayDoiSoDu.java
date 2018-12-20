package damthai.com.moneymanagement.View;

import android.view.View;
import android.widget.Toast;

import damthai.com.moneymanagement.MainActivity;

public class ViewThayDoiSoDu implements ViewImpThayDoiSoDu {

    private boolean TrangThai;

    public boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    @Override
    public void ThayDoiThanhCong() {
        Toast.makeText(MainActivity.getInstance(),"Thay đổi thành công!",Toast.LENGTH_SHORT).show();
        this.TrangThai = true;
    }

    @Override
    public void ChuaNhapGiaTriThayDoi() {
        Toast.makeText(MainActivity.getInstance(),"Chưa nhập số tiền thay đổi!",Toast.LENGTH_SHORT).show();
        this.TrangThai = false;
    }
}
