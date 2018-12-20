package damthai.com.moneymanagement.View;

import android.widget.Toast;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.Nhom;

public class ViewChinhLy implements ViewImpChinhLy
{
    private boolean TrangThai;

    public boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean trangThai) {
        TrangThai = trangThai;
    }

    @Override
    public void TenNhomDaTonTai(Nhom nhom) {
        Toast.makeText(MainActivity.getInstance(),"Nhóm " +nhom.getTennhom()+ " đã tồn tại! Vui lòng kiểm tra lại!",Toast.LENGTH_SHORT).show();
        this.TrangThai = false;
    }

    @Override
    public void SuaNhomThanhCong() {
        Toast.makeText(MainActivity.getInstance(),"Sửa đổi nhóm thành công!",Toast.LENGTH_SHORT).show();
        this.TrangThai = true;

    }
}
