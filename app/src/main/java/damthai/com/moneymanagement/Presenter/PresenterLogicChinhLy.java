package damthai.com.moneymanagement.Presenter;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.GiaoDich;
import damthai.com.moneymanagement.Model.Nhom;
import damthai.com.moneymanagement.Model.NhomAdapter;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.R;
import damthai.com.moneymanagement.View.ViewChinhLy;
import damthai.com.moneymanagement.View.ViewGiaoDich;
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
        ArrayList<GiaoDich> listGiaoDich = dbManager.getGiaoDich(taiKhoan.getMataikhoan());
        for (int i=0;i<listGiaoDich.size();i++){
            if(listGiaoDich.get(i).getNhom()==nhom.getManhom()) {
                for (int j=0;j<listNhom.size();j++) {
                    if(listNhom.get(i).getManhom()==nhom.getManhom()) {
                        if(listNhom.get(i).getLoai() != nhom.getLoai()) {
                            viewImpChinhLy.KhongTheThayDoiLoai();
                            dem = 1;
                            break;
                        }
                        break;
                    }
                }
                break;
            }
        }

        for (int i=0;i<listNhom.size();i++)
        {
            if(nhom.getTennhom().equals(listNhom.get(i).getTennhom()) && listNhom.get(i).getManhom() != nhom.getManhom()) {
                viewImpChinhLy.TenNhomDaTonTai(nhom);
                dem = 1;
                break;
            }
        }
        for (int i=0;i<listNhom.size();i++){
            if(nhom.getManhom()==listNhom.get(i).getManhom()){
                if (nhom.getTennhom().equals(listNhom.get(i).getTennhom()) && nhom.getLoai() == listNhom.get(i).getLoai()) {
                    viewImpChinhLy.None();
                    dem = 1;
                    break;
                }
            }
        }

        if (dem == 0)
        {
            dbManager.updateNhom(nhom);
            viewImpChinhLy.SuaNhomThanhCong();
        }
    }

    public void xoaNhom(Nhom nhom)
    {
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<GiaoDich> listGiaoDich = dbManager.getGiaoDich(nhom.getMataikhoan());
        int dem = 0;
        for(int i=0;i<listGiaoDich.size();i++) {
            if (listGiaoDich.get(i).getNhom() == nhom.getManhom()) {
                viewImpChinhLy.NhomDangDuocSuDung();
                dem = 1;
                break;
            }
        }
        if(dem ==0) {
            dbManager.deleteNhom(nhom);
            viewImpChinhLy.XoaNhomThanhCong();
        }
    }

   public boolean KiemTraNhomSuDung(TaiKhoan taiKhoan, Nhom nhom)
    {
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<GiaoDich> listGD = dbManager.getGiaoDich(taiKhoan.getMataikhoan());
        for(int i = 0;i<listGD.size();i++)
            if(nhom.getManhom() ==listGD.get(i).getNhom())
                return true;
        return false;
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
