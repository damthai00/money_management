package damthai.com.moneymanagement.Presenter;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.Nhom;
import damthai.com.moneymanagement.Model.NhomAdapter;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.R;
import damthai.com.moneymanagement.View.ViewChinhLy;
import damthai.com.moneymanagement.View.ViewImpChinhLy;

public class PresenterLogicChinhLy implements PresenterImpChinhLy {

    ViewImpChinhLy viewImpChinhLy;

    public PresenterLogicChinhLy(ViewImpChinhLy viewImpChinhLy)
    {
        this.viewImpChinhLy = viewImpChinhLy;
    }

    @Override
    public TaiKhoan LoadDuLieu_TaiKhoan(TaiKhoan taiKhoan) {
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<TaiKhoan> list = dbManager.getTaiKhoan();
        for (int i = 0;i<list.size();i++) {
            if (taiKhoan.getMataikhoan() == list.get(i).getMataikhoan()) {
                taiKhoan = list.get(i);
            }
        }
        return taiKhoan;
    }

    public Nhom getNhom(int posituon,TaiKhoan taiKhoan)
    {
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> list = dbManager.getNhom(taiKhoan.getMataikhoan());
        return list.get(posituon);
    }

    public void suaNhom(Nhom nhom,TaiKhoan taiKhoan)
    {
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(taiKhoan.getMataikhoan());
        int dem = 0;

        for (int i=0;i<listNhom.size();i++)
        {
            if(nhom.getTennhom().equals(listNhom.get(i).getTennhom())) {
                viewImpChinhLy.TenNhomDaTonTai(nhom);
                dem = 1;
                break;
            }
        }
        if (dem == 0)
        {
            dbManager.updateNhom(nhom);
            viewImpChinhLy.SuaNhomThanhCong();
        }
    }

    @Override
    public ListView loadDuLiau_ListNhom(ListView listView,TaiKhoan taiKhoan){
        final DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = new ArrayList<>();
        listNhom = dbManager.getNhom(taiKhoan.getMataikhoan());
        NhomAdapter nhomAdapter = new NhomAdapter(MainActivity.getInstance(), R.layout.danh_sach_nhom,listNhom);
        listView.setAdapter(nhomAdapter);

        if(listView == null)
            return null;
        return listView;
    }

}
