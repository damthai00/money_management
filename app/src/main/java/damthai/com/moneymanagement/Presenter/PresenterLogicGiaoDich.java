package damthai.com.moneymanagement.Presenter;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.GiaoDich;
import damthai.com.moneymanagement.Model.GiaoDichAdapter;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.R;
import damthai.com.moneymanagement.View.ViewImpGiaoDich;

public class PresenterLogicGiaoDich implements PresenterImpGiaoDich {

    ViewImpGiaoDich viewImpGiaoDich;
    public  PresenterLogicGiaoDich(ViewImpGiaoDich viewImpGiaoDich){
        this.viewImpGiaoDich = viewImpGiaoDich;
    }

    public PresenterLogicGiaoDich()
    {

    }
    public ListView loadDuLiau_DSGiaoDich(ListView listDSGiaoDich,int ma_tai_khoan ){
        final DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<GiaoDich> list = new ArrayList<>();
        list = dbManager.getGiaoDich(ma_tai_khoan);
        //đảo ngược
        Collections.reverse(list);
        GiaoDichAdapter giaoDichAdapter = new GiaoDichAdapter(MainActivity.getInstance(), R.layout.danhsach_giaodich,list);
        listDSGiaoDich.setAdapter(giaoDichAdapter);

        if(listDSGiaoDich == null)
            return null;
        return listDSGiaoDich;
    }

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

}
