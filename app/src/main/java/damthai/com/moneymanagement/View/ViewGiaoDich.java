package damthai.com.moneymanagement.View;

import android.widget.Toast;

import damthai.com.moneymanagement.MainActivity;

public class ViewGiaoDich implements ViewImpGiaoDich {
    private boolean TrangThai = false;

    public boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }


    @Override
    public void SuaGiaoDichThanhCong() {
        Toast.makeText(MainActivity.getInstance(),"Sửa đổi giao dịch thành công!",Toast.LENGTH_SHORT).show();
        this.TrangThai = true;
    }

    @Override
    public void ChuaNhapSoTien() {
        Toast.makeText(MainActivity.getInstance(),"Vui lòng nhập số tiền!",Toast.LENGTH_SHORT).show();
        this.TrangThai = false;
    }

    @Override
    public void XoaThanhCong() {
        Toast.makeText(MainActivity.getInstance(),"Xóa giao dịch thành công!",Toast.LENGTH_SHORT).show();
        this.TrangThai = true;
    }

    @Override
    public void None() {
        this.TrangThai = true;
    }
}
