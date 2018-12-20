package damthai.com.moneymanagement.View;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import damthai.com.moneymanagement.MainActivity;

public class ViewChuyenDoi implements ViewIpmChuyenDoi {

    ViewChuyenDoi(){
    }
    @Override
    public void ChuaChonHinhThucChuyenTien() {
        Toast.makeText(MainActivity.getInstance(),"Vui lòng chọn hình thức chuyển tiền!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ChuaChonHinhThucTraPhi() {
        Toast.makeText(MainActivity.getInstance(),"Vui lòng chọn hình thức trả phí!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ChuyenTienThanhCong() {
        Toast.makeText(MainActivity.getInstance(),"Chuyển tiền thành công!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void RutTienThanhCong() {
        Toast.makeText(MainActivity.getInstance(),"Rút tiền thành công!",Toast.LENGTH_SHORT).show();
    }
    public void ChuaNhapSoTienChuyenDoi()
    {
        Toast.makeText(MainActivity.getInstance(),"Chưa nhập số tiền chuyển đổi!",Toast.LENGTH_SHORT).show();
    }
}
