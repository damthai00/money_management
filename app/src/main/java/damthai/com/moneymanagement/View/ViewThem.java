package damthai.com.moneymanagement.View;

import android.widget.Toast;

import damthai.com.moneymanagement.MainActivity;

public class ViewThem implements ViewImpThem {

    private boolean TrangThai = false;

    public boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    @Override
    public void ChuaNhapSoTien() {
        Toast.makeText(MainActivity.getInstance(),"Vui lòng nhập số tiền!",Toast.LENGTH_SHORT).show();
        TrangThai = false;
    }

    @Override
    public void ChuaChonNhom() {
        Toast.makeText(MainActivity.getInstance(),"Vui lòng chọn nhóm!",Toast.LENGTH_SHORT).show();
        TrangThai = false;
    }

    @Override
    public void ChuaChonHinhThucThanhToan() {
        Toast.makeText(MainActivity.getInstance(),"Chưa Chọn hình thức thanh toán",Toast.LENGTH_SHORT).show();
        TrangThai = false;
    }

    public void ThemThanhCong() {
        Toast.makeText(MainActivity.getInstance(),"Thêm giao dịch thành công!",Toast.LENGTH_SHORT).show();
        TrangThai = true;
    }

}
