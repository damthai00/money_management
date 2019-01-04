package damthai.com.moneymanagement.View;

import android.widget.Toast;

import java.security.cert.TrustAnchor;

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

    @Override
    public void NhomDangDuocSuDung() {
        Toast.makeText(MainActivity.getInstance(),"Nhóm đang được sử dụng! Không thể xóa! Hãy xem lại!",Toast.LENGTH_SHORT).show();
        this.TrangThai = false;
    }

    @Override
    public void XoaNhomThanhCong() {
        Toast.makeText(MainActivity.getInstance(),"Xóa nhóm thành công!",Toast.LENGTH_SHORT).show();
        this.TrangThai = true;
    }

    @Override
    public void KhongTheThayDoiLoai() {
        Toast.makeText(MainActivity.getInstance(),"Nhóm đang được sử dụng! Chỉ có thể thay đổi tên nhóm!",Toast.LENGTH_SHORT).show();
        TrangThai = false;
    }

    @Override
    public void None() {
        TrangThai = true;
    }
}
