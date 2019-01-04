package damthai.com.moneymanagement.View;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import damthai.com.moneymanagement.MainActivity;

public class ViewTaoNhom implements ViewImpTaoNhom {

    private boolean TrangThai = false;
    public boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }



    @Override
    public void TaoNhomThanhCong(String tennhomtao) {
        Toast.makeText(MainActivity.getInstance(),"Tạo nhóm  \"" + tennhomtao + "\"  thành công!",Toast.LENGTH_SHORT).show();
        this.setTrangThai(true);
    }

    @Override
    public void NhomDaTonTai(String tentaonhom) {
        Toast.makeText(MainActivity.getInstance(),"Nhóm  \"" +tentaonhom+ "\"  đã tồn tại! Vui lòng nhập lại tên nhóm!",Toast.LENGTH_SHORT).show();
        this.setTrangThai(false);
    }

    @Override
    public void ChuaChonHinhThucThanhToan() {
        Toast.makeText(MainActivity.getInstance(),"Vui lòng chọn hình thức thanh toán!",Toast.LENGTH_SHORT).show();
        this.setTrangThai(false);
    }

    public void ChuaNhapTenNhom()
    {
        Toast.makeText(MainActivity.getInstance(),"Vui lòng nhập tên nhóm!",Toast.LENGTH_SHORT).show();
        this.setTrangThai(false);
    }
}
