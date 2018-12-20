package damthai.com.moneymanagement.Presenter;

import android.hardware.camera2.DngCreator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.GiaoDich;
import damthai.com.moneymanagement.Model.GiaoDichAdapter;
import damthai.com.moneymanagement.Model.Nhom;
import damthai.com.moneymanagement.Model.NhomAdapter;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.R;
import damthai.com.moneymanagement.View.ViewImpThem;
import damthai.com.moneymanagement.View.ViewThem;

public class PresenterLogicThem  implements PresenterImpThem{

    private ViewImpThem viewImpThem;
    public PresenterLogicThem(ViewImpThem viewImpThem)
    {
        this.viewImpThem = viewImpThem;
    }


    @Override
    public ListView LoadDSNhom(ListView listView,TaiKhoan taiKhoan) {
        final DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = new ArrayList<>();
        listNhom = dbManager.getNhom(taiKhoan.getMataikhoan());
        NhomAdapter nhomAdapter = new NhomAdapter(MainActivity.getInstance(), R.layout.danh_sach_nhom,listNhom);
        listView.setAdapter(nhomAdapter);

        if(listView == null)
            return null;
        return listView;
    }

    public void ThemGiaoDich(GiaoDich giaoDich)
    {
        if(giaoDich.getSotien() < 0)
            viewImpThem.ChuaNhapSoTien();
        else {
            if(giaoDich.getNhom() == -1)
                viewImpThem.ChuaChonNhom();
            else {
                if(giaoDich.getHinhthucphi().equals(""))
                    viewImpThem.ChuaChonHinhThucThanhToan();
                else {
                    DBManager dbManager = new DBManager(MainActivity.getInstance());
                    ArrayList<TaiKhoan> listTaiKhoan= dbManager.getTaiKhoan();
                    ArrayList<Nhom> listNhom = dbManager.getNhom(giaoDich.getMataikhoan());
                    int loai = 0;
                    for(int i=0;i<listNhom.size();i++)
                        if(giaoDich.getNhom() == listNhom.get(i).getManhom()){
                            loai = listNhom.get(i).getLoai();
                            break;
                        }


                    for(int i = 0;i<listTaiKhoan.size();i++)
                        if(listTaiKhoan.get(i).getMataikhoan() == giaoDich.getMataikhoan())
                        {
                            if(giaoDich.getHinhthucphi()=="tienmat" &&loai == 1)
                                listTaiKhoan.get(i).setTienmat(listTaiKhoan.get(i).getTienmat()+giaoDich.getSotien()) ;
                            if(giaoDich.getHinhthucphi()=="taikhoanthe" &&loai ==1)
                                listTaiKhoan.get(i).setTaikhoanthe(listTaiKhoan.get(i).getTaikhoanthe()+giaoDich.getSotien());
                            if(giaoDich.getHinhthucphi()=="tienmat" &&loai == 2)
                                listTaiKhoan.get(i).setTienmat(listTaiKhoan.get(i).getTienmat()-giaoDich.getSotien()) ;
                            if(giaoDich.getHinhthucphi()=="taikhoanthe" &&loai==2)
                                listTaiKhoan.get(i).setTaikhoanthe(listTaiKhoan.get(i).getTaikhoanthe()-giaoDich.getSotien());
                            dbManager.updateTaikhoan(listTaiKhoan.get(i));
                            break;
                        }
                    dbManager.addGiaoDich(giaoDich);
                    viewImpThem.ThemThanhCong();
                }
            }
        }
    }
}
