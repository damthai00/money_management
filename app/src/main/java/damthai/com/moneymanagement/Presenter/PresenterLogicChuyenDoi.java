package damthai.com.moneymanagement.Presenter;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.ChuyenDoi;
import damthai.com.moneymanagement.Model.ChuyenDoiAdapter;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.GiaoDich;
import damthai.com.moneymanagement.Model.GiaoDichAdapter;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.R;
import damthai.com.moneymanagement.View.ChuyenDoiFragment;
import damthai.com.moneymanagement.View.ViewChuyenDoi;
import damthai.com.moneymanagement.View.ViewIpmChuyenDoi;

public class PresenterLogicChuyenDoi implements PresenterImpChuyenDoi{

        ViewIpmChuyenDoi viewIpmChuyenDoi;
    public PresenterLogicChuyenDoi(ViewChuyenDoi viewChuyenDoi)
    {
        this.viewIpmChuyenDoi = viewChuyenDoi;
    }

    public PresenterLogicChuyenDoi()
    {
    }

    @Override
    public void XuLyChuyenDoi(TaiKhoan taiKhoan, RadioButton ruttien, RadioButton chuyentien, EditText sotien,
                              EditText phi, RadioButton taikhoanthe, RadioButton tienmat,EditText ngaychuyendoi, EditText ghichu) {
        ChuyenDoi chuyenDoi = new ChuyenDoi(0,0,"","","","",taiKhoan.getMataikhoan());
        chuyenDoi.setGhichu(ghichu.getText().toString());
        chuyenDoi.setNgaychuyendoi(ngaychuyendoi.getText().toString());

        if(chuyentien.isChecked() ==false && ruttien.isChecked() == false)
            viewIpmChuyenDoi.ChuaChonHinhThucChuyenTien();
        else
            if (taikhoanthe.isChecked() == false && tienmat.isChecked()==false)
                    viewIpmChuyenDoi.ChuaChonHinhThucTraPhi();
            else
                if(sotien.getText().toString().equals("")) {
                    viewIpmChuyenDoi.ChuaNhapSoTienChuyenDoi();
                }else {
                    if(phi.getText().toString().equals(""))
                        phi.setText("0");
                    else{
                        long tien_phi = Long.parseLong(phi.getText().toString());
                        chuyenDoi.setPhi(tien_phi);
                    }
                    long sotien_taikhoanthe,sotien_tienmat,sotien_them,sotien_phi;
                    sotien_taikhoanthe = taiKhoan.getTaikhoanthe();
                    sotien_tienmat = taiKhoan.getTienmat();
                    sotien_them = Long.parseLong(String.valueOf(sotien.getText()));
                    sotien_phi = Long.parseLong(String.valueOf(phi.getText()));
                    chuyenDoi.setSotien(sotien_them);

                    if(ruttien.isChecked()==true && tienmat.isChecked()==true)
                    {
                        chuyenDoi.setLoaiphi("tienmat");
                        chuyenDoi.setHinhthuc("ruttien");
                        sotien_taikhoanthe = sotien_taikhoanthe - sotien_them;
                        sotien_tienmat = sotien_tienmat + sotien_them - sotien_phi;
                        viewIpmChuyenDoi.RutTienThanhCong();
                    }
                    else if (ruttien.isChecked() == true && taikhoanthe.isChecked() ==true)
                    {
                        chuyenDoi.setHinhthuc("ruttien");
                        chuyenDoi.setLoaiphi("taikhoanthe");
                        sotien_taikhoanthe = sotien_taikhoanthe - sotien_them - sotien_phi;
                        sotien_tienmat = sotien_tienmat + sotien_them;
                        viewIpmChuyenDoi.RutTienThanhCong();
                    }
                    if(chuyentien.isChecked()==true && tienmat.isChecked() == true)
                    {
                        chuyenDoi.setHinhthuc("chuyentien");
                        chuyenDoi.setLoaiphi("tienmat");
                        sotien_taikhoanthe = sotien_taikhoanthe + sotien_them;
                        sotien_tienmat = sotien_tienmat - sotien_them- sotien_phi;
                        viewIpmChuyenDoi.ChuyenTienThanhCong();
                    }else if(chuyentien.isChecked() == true && taikhoanthe.isChecked() == true)
                    {
                        chuyenDoi.setHinhthuc("chuyentien");
                        chuyenDoi.setLoaiphi("taikhoanthe");
                        sotien_taikhoanthe = sotien_taikhoanthe + sotien_them -sotien_phi;
                        sotien_tienmat = sotien_tienmat - sotien_them;
                        viewIpmChuyenDoi.ChuyenTienThanhCong();
                    }
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.setTaikhoan_using(sotien_taikhoanthe,sotien_tienmat);

                    taiKhoan.setTaikhoanthe(sotien_taikhoanthe);
                    taiKhoan.setTienmat(sotien_tienmat);
                    final DBManager dbManager = new DBManager(MainActivity.getInstance());
                    dbManager.addChuyenDoi(chuyenDoi);
                    dbManager.updateTaikhoan(taiKhoan);
                }
    }
    public TaiKhoan LoadDuLieu(TaiKhoan taiKhoan)
    {
        final  DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<TaiKhoan> list = dbManager.getTaiKhoan();
        for (int i = 0;i<list.size();i++)
        {
           if( taiKhoan.getMataikhoan() == list.get(i).getMataikhoan())
           {
               taiKhoan = list.get(i);
           }
        }
                return taiKhoan;
    }
    public ListView LoadDanhSachChuyenDoi(ListView lv_ds_chuyendoi,int ma_tai_khoan)
    {
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<ChuyenDoi> listChuyenDoi = dbManager.getChuyenDoi(ma_tai_khoan);
        ChuyenDoiAdapter chuyenDoiAdapter = new ChuyenDoiAdapter(MainActivity.getInstance(), R.layout.danh_sach_chuyendoi,listChuyenDoi);
        lv_ds_chuyendoi.setAdapter(chuyenDoiAdapter);
        if(lv_ds_chuyendoi==null)
            return null;
        return lv_ds_chuyendoi;
    }

    public ChuyenDoi getItemChuyenDoi(int position, int ma_tai_khoan)
    {
        DBManager dbManager  =new DBManager(MainActivity.getInstance());
        ArrayList<ChuyenDoi> listChuyenDoi = dbManager.getChuyenDoi(ma_tai_khoan);
        if(listChuyenDoi==null)
            return null;
        return listChuyenDoi.get(position);
    }
    @Override
    public void SuaChuyenDoi(ChuyenDoi chuyenDoiMoi)
    {
        if(chuyenDoiMoi.getSotien() == 0)
            viewIpmChuyenDoi.ChuaNhapSoTienChuyenDoiMoi();

        else {
            DBManager dbManager = new DBManager(MainActivity.getInstance());
            ArrayList<ChuyenDoi> listChuyenDoi = dbManager.getChuyenDoi(chuyenDoiMoi.getMataikhoan());
            ChuyenDoi chuyenDoiCu = new ChuyenDoi();
            for (int i = 0; i < listChuyenDoi.size(); i++)
                if (chuyenDoiMoi.getMachuyendoi() == listChuyenDoi.get(i).getMachuyendoi()) {
                    chuyenDoiCu = listChuyenDoi.get(i);
                    break;
                }
            if (chuyenDoiMoi.getSotien() == chuyenDoiCu.getSotien() &&
                    chuyenDoiCu.getPhi() == chuyenDoiMoi.getPhi() &&
                    chuyenDoiMoi.getLoaiphi().equals(chuyenDoiCu.getLoaiphi()) &&
                    chuyenDoiMoi.getHinhthuc().equals(chuyenDoiCu.getHinhthuc()) &&
                    chuyenDoiMoi.getNgaychuyendoi().equals(chuyenDoiCu.getNgaychuyendoi()) &&
                    chuyenDoiMoi.getGhichu().equals(chuyenDoiCu.getGhichu()))
                viewIpmChuyenDoi.None();


            else {
                ArrayList<TaiKhoan> listTaiKhoan = dbManager.getTaiKhoan();
                TaiKhoan taiKhoan = new TaiKhoan();
                long tkt = 0;
                long tm = 0;
                for (int i = 0; i < listTaiKhoan.size(); i++)
                    if (chuyenDoiMoi.getMataikhoan() == listTaiKhoan.get(i).getMataikhoan()) {
                        taiKhoan = listTaiKhoan.get(i);
                        break;
                    }
                tkt = taiKhoan.getTaikhoanthe();
                tm = taiKhoan.getTienmat();
                if (chuyenDoiCu.getHinhthuc().equals("chuyentien") && chuyenDoiCu.getLoaiphi().equals("tienmat")) {
                    tkt = tkt - chuyenDoiCu.getSotien();
                    tm = tm + chuyenDoiCu.getSotien() + chuyenDoiCu.getPhi();
                } else {
                    if (chuyenDoiCu.getHinhthuc().equals("chuyentien") && chuyenDoiCu.getLoaiphi().equals("taikhoanthe")) {
                        tkt = tkt - chuyenDoiCu.getSotien() + chuyenDoiCu.getPhi();
                        tm = tm + chuyenDoiCu.getSotien();
                    } else {
                        if (chuyenDoiCu.getHinhthuc().equals("ruttien") && chuyenDoiCu.getLoaiphi().equals("tienmat")) {
                            tkt = tkt + chuyenDoiCu.getSotien();
                            tm = tm - chuyenDoiCu.getSotien() + chuyenDoiCu.getPhi();
                        } else {
                            if (chuyenDoiCu.getHinhthuc().equals("ruttien") && chuyenDoiCu.getLoaiphi().equals("taikhoanthe")) {
                                tkt = tkt + chuyenDoiCu.getSotien() + chuyenDoiCu.getPhi();
                                tm = tm - chuyenDoiCu.getSotien();
                            }
                        }
                    }
                }

                if (chuyenDoiMoi.getHinhthuc().equals("chuyentien") && chuyenDoiMoi.getLoaiphi().equals("tienmat")) {
                    tkt = tkt + chuyenDoiMoi.getSotien();
                    tm = tm - chuyenDoiMoi.getSotien() - chuyenDoiMoi.getPhi();
                } else {
                    if (chuyenDoiMoi.getHinhthuc().equals("chuyentien") && chuyenDoiMoi.getLoaiphi().equals("taikhoanthe")) {
                        tkt = tkt + chuyenDoiMoi.getSotien() - chuyenDoiMoi.getPhi();
                        tm = tm - chuyenDoiMoi.getSotien();
                    } else {
                        if (chuyenDoiMoi.getHinhthuc().equals("ruttien") && chuyenDoiMoi.getLoaiphi().equals("tienmat")) {
                            tkt = tkt - chuyenDoiMoi.getSotien();
                            tm = tm + chuyenDoiMoi.getSotien() - chuyenDoiMoi.getPhi();
                        } else {
                            if (chuyenDoiMoi.getHinhthuc().equals("ruttien") && chuyenDoiMoi.getLoaiphi().equals("taikhoanthe")) {
                                tkt = tkt - chuyenDoiMoi.getSotien() - chuyenDoiMoi.getPhi();
                                tm = tm + chuyenDoiMoi.getSotien();
                            }
                        }
                    }
                }
                taiKhoan.setTaikhoanthe(tkt);
                taiKhoan.setTienmat(tm);
                dbManager.updateTaikhoan(taiKhoan);
                dbManager.updateChuyenDoi(chuyenDoiMoi);
                viewIpmChuyenDoi.SuaChuyenDoiThanhCong();
            }
        }

    }

    @Override
    public void XoaChuyenDoi(ChuyenDoi chuyenDoiCu)
    {
        TaiKhoan taiKhoan = new TaiKhoan();
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<TaiKhoan> listTaiKhoan = dbManager.getTaiKhoan();
        for(int  i = 0;i<listTaiKhoan.size();i++)
            if(chuyenDoiCu.getMataikhoan()==listTaiKhoan.get(i).getMataikhoan()){
                taiKhoan = listTaiKhoan.get(i);
                break;
            }
        long tkt = taiKhoan.getTaikhoanthe();
        long tm = taiKhoan.getTienmat();

        if (chuyenDoiCu.getHinhthuc().equals("chuyentien") && chuyenDoiCu.getLoaiphi().equals("tienmat")) {
            tkt = tkt - chuyenDoiCu.getSotien();
            tm = tm + chuyenDoiCu.getSotien() + chuyenDoiCu.getPhi();
        } else {
            if (chuyenDoiCu.getHinhthuc().equals("chuyentien") && chuyenDoiCu.getLoaiphi().equals("taikhoanthe")) {
                tkt = tkt - chuyenDoiCu.getSotien() + chuyenDoiCu.getPhi();
                tm = tm + chuyenDoiCu.getSotien();
            } else {
                if (chuyenDoiCu.getHinhthuc().equals("ruttien") && chuyenDoiCu.getLoaiphi().equals("tienmat")) {
                    tkt = tkt + chuyenDoiCu.getSotien();
                    tm = tm - chuyenDoiCu.getSotien() + chuyenDoiCu.getPhi();
                } else {
                    if (chuyenDoiCu.getHinhthuc().equals("ruttien") && chuyenDoiCu.getLoaiphi().equals("taikhoanthe")) {
                        tkt = tkt + chuyenDoiCu.getSotien() + chuyenDoiCu.getPhi();
                        tm = tm - chuyenDoiCu.getSotien();
                    }
                }
            }
        }
        taiKhoan.setTaikhoanthe(tkt);
        taiKhoan.setTienmat(tm);
        dbManager.updateTaikhoan(taiKhoan);
        dbManager.deleteChuyenDoi(chuyenDoiCu);
        viewIpmChuyenDoi.XoaThanhCong();
    }
}
