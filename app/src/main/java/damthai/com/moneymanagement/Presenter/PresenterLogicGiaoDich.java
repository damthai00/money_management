package damthai.com.moneymanagement.Presenter;

import android.widget.ListView;
import android.widget.Toast;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Calendar;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.GiaoDich;
import damthai.com.moneymanagement.Model.GiaoDichAdapter;
import damthai.com.moneymanagement.Model.Nhom;
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

    public ListView GiaoDichTheoNgay(ListView listDSGiaoDich,int ma_tai_khoan )
    {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<GiaoDich> listGDTheoNgay = this.XapXepTheoNgay(simpleDateFormat.format(now),ma_tai_khoan);
        Collections.reverse(listGDTheoNgay);
        GiaoDichAdapter giaoDichAdapter = new GiaoDichAdapter(MainActivity.getInstance(), R.layout.danhsach_giaodich,listGDTheoNgay);
        listDSGiaoDich.setAdapter(giaoDichAdapter);
        if(listDSGiaoDich == null)
            return null;
        return listDSGiaoDich;
    }
    public ListView GiaoDichTheoThang(ListView listDSGiaoDich,int ma_tai_khoan )
    {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<GiaoDich> listGDTheoNgay = this.XapXepTheoThang(simpleDateFormat.format(now),ma_tai_khoan);
        Collections.reverse(listGDTheoNgay);
        GiaoDichAdapter giaoDichAdapter = new GiaoDichAdapter(MainActivity.getInstance(), R.layout.danhsach_giaodich,listGDTheoNgay);
        listDSGiaoDich.setAdapter(giaoDichAdapter);
        if(listDSGiaoDich == null)
            return null;
        return listDSGiaoDich;
    }
    public ListView GiaoDichTheoTuan(ListView listDSGiaoDich,int ma_tai_khoan )
    {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<GiaoDich> listGDTheoNgay = this.XapXepTheoTuan(simpleDateFormat.format(now),ma_tai_khoan);
        Collections.reverse(listGDTheoNgay);
        GiaoDichAdapter giaoDichAdapter = new GiaoDichAdapter(MainActivity.getInstance(), R.layout.danhsach_giaodich,listGDTheoNgay);
        listDSGiaoDich.setAdapter(giaoDichAdapter);
        if(listDSGiaoDich == null)
            return null;
        return listDSGiaoDich;
    }
    public ListView GiaoDichTheoThang_Truoc(ListView listDSGiaoDich,int ma_tai_khoan )
    {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.MONTH, -1);
        ArrayList<GiaoDich> listGDTheoNgay = this.XapXepTheoThang(simpleDateFormat.format(calendar.getTime()),ma_tai_khoan);
        Collections.reverse(listGDTheoNgay);
        GiaoDichAdapter giaoDichAdapter = new GiaoDichAdapter(MainActivity.getInstance(), R.layout.danhsach_giaodich,listGDTheoNgay);
        listDSGiaoDich.setAdapter(giaoDichAdapter);
        if(listDSGiaoDich == null)
            return null;
        return listDSGiaoDich;
    }
    public ListView GiaoDichTheoTuan_Truoc(ListView listDSGiaoDich,int ma_tai_khoan )
    {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.WEEK_OF_YEAR, -1);
        ArrayList<GiaoDich> listGDTheoNgay = this.XapXepTheoTuan(simpleDateFormat.format(calendar.getTime()),ma_tai_khoan);
        Collections.reverse(listGDTheoNgay);
        GiaoDichAdapter giaoDichAdapter = new GiaoDichAdapter(MainActivity.getInstance(), R.layout.danhsach_giaodich,listGDTheoNgay);
        listDSGiaoDich.setAdapter(giaoDichAdapter);
        if(listDSGiaoDich == null)
            return null;
        return listDSGiaoDich;
    }


    public ArrayList<GiaoDich> XapXepTheoNgay(String dateString,int ma_tai_khoan)
    {
        final DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<GiaoDich> list =dbManager.getGiaoDich(ma_tai_khoan);
        ArrayList<GiaoDich> listGDTheoNgay = new ArrayList<>();
        for(int i =0;i<list.size();i++)
        {
            if(dateString.equals(list.get(i).getNgaygiaodich()))
                listGDTheoNgay.add(list.get(i));
        }
        return  listGDTheoNgay;
    }
    public ArrayList<GiaoDich> XapXepTheoThang(String dateString,int ma_tai_khoan)
    {
        final DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<GiaoDich> list =dbManager.getGiaoDich(ma_tai_khoan);
       int month = this.LayThangTrongChuoi(dateString);
        int year = this.LayNamTrongChuoi(dateString);
        ArrayList<GiaoDich> listDGTheoThang = new ArrayList<>();
        for(int i =0;i<list.size();i++)
        {
            if(this.LayThangTrongChuoi(list.get(i).getNgaygiaodich()) == month && this.LayNamTrongChuoi(list.get(i).getNgaygiaodich())==year)
                listDGTheoThang.add(list.get(i));
        }
        return listDGTheoThang;
    }
    public ArrayList<GiaoDich> XapXepTheoTuan(String dateString,int ma_tai_khoan)
    {
        final DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<GiaoDich> list =dbManager.getGiaoDich(ma_tai_khoan);
        int week = this.LayTuanTrongChuoi(dateString);
        int year = this.LayNamTrongChuoi(dateString);
        ArrayList<GiaoDich> listDGTheoTuan = new ArrayList<>();
        for(int i =0;i<list.size();i++)
        {
            if(this.LayTuanTrongChuoi(list.get(i).getNgaygiaodich()) == week && this.LayNamTrongChuoi(list.get(i).getNgaygiaodich())==year){
                listDGTheoTuan.add(list.get(i));

            }
            if(this.LayTuanTrongChuoi(list.get(i).getNgaygiaodich()) == week && this.LayNamTrongChuoi(list.get(i).getNgaygiaodich())==year-1){
                listDGTheoTuan.add(list.get(i));

            }
            if(this.LayTuanTrongChuoi(list.get(i).getNgaygiaodich()) == week && this.LayNamTrongChuoi(list.get(i).getNgaygiaodich())==year+1){
                listDGTheoTuan.add(list.get(i));

            }
        }
        return listDGTheoTuan;
    }



    public int LayThangTrongChuoi(String dateString)
    {
        Date date = new Date();
        ParsePosition parsePosition = new ParsePosition(0);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = simpleDateFormat.parse(dateString,parsePosition);
        calendar.setTime(date);
        int month = calendar.get(calendar.MONTH);
        return month;
    }

    public int LayNgayTrongChuoi(String dateString)
    {
        Date date = new Date();
        ParsePosition parsePosition = new ParsePosition(0);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = simpleDateFormat.parse(dateString,parsePosition);
        calendar.setTime(date);
        int ngay = calendar.get(calendar.DAY_OF_MONTH);
        return ngay;
    }

    public int LayNamTrongChuoi(String dateString)
    {
        Date date = new Date();
        ParsePosition parsePosition = new ParsePosition(0);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = simpleDateFormat.parse(dateString,parsePosition);
        calendar.setTime(date);
        int year = calendar.get(calendar.YEAR);
        return year;
    }
    public int LayTuanTrongChuoi(String dateString)
    {
        Date date = new Date();
        ParsePosition parsePosition = new ParsePosition(0);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = simpleDateFormat.parse(dateString,parsePosition);
        calendar.setTime(date);
        int week = calendar.get(calendar.WEEK_OF_YEAR);
        return week;
    }

    /////set onclick --------------------------------------------------------------

    public GiaoDich SuaGiaoDich_TuanNay(int position,int ma_tai_khoan)
    {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
       ArrayList<GiaoDich> list = this.XapXepTheoTuan(simpleDateFormat.format(now),ma_tai_khoan);
        Collections.reverse(list);

        return list.get(position);
    }

    public GiaoDich SuaGiaoDich_HomNay(int position,int ma_tai_khoan) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoNgay(simpleDateFormat.format(now), ma_tai_khoan);
        Collections.reverse(list);
        return list.get(position);
    }

    public GiaoDich SuaGiaoDich_ThangNay(int position,int ma_tai_khoan)
    {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoThang(simpleDateFormat.format(now),ma_tai_khoan);
        Collections.reverse(list);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        return list.get(position);
    }

    public GiaoDich SuaGiaoDich_TuanTruoc(int position,int ma_tai_khoan)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.WEEK_OF_YEAR,-1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoTuan(simpleDateFormat.format(calendar.getTime()),ma_tai_khoan);
        Collections.reverse(list);
        return list.get(position);
    }
    public GiaoDich SuaGiaoDich_ThangTruoc(int position,int ma_tai_khoan)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.MONTH,-1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoThang(simpleDateFormat.format(calendar.getTime()),ma_tai_khoan);
        Collections.reverse(list);
        return list.get(position);
    }



    public GiaoDich SuaGiaoDich_TatCa(int position,int ma_tai_khoan)
    {
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<GiaoDich> list = dbManager.getGiaoDich(ma_tai_khoan);
        Collections.reverse(list);
        return list.get(position);
    }

    public Nhom getNhom(GiaoDich giaoDich)
    {
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> list = dbManager.getNhom(giaoDich.getMataikhoan());
        for(int i=0;i<list.size();i++)
            if(list.get(i).getManhom() == giaoDich.getNhom())
                return list.get(i);
        return null;
    }
    @Override
    public void SuaDoiGiaoDich(GiaoDich giaoDichMoi)
    {
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<GiaoDich> listGiaoDich = dbManager.getGiaoDich(giaoDichMoi.getMataikhoan());
        ArrayList<TaiKhoan> listTaiKhoan = dbManager.getTaiKhoan();
        ArrayList<Nhom> listNhom = dbManager.getNhom(giaoDichMoi.getMataikhoan());
        TaiKhoan taiKhoan = new TaiKhoan();
        Nhom nhomcu = new Nhom();
        Nhom nhommoi = new Nhom();
        long tkt = 0;
        long tm = 0;
        GiaoDich giaoDichCu = new GiaoDich();
        for(int i = 0;i<listGiaoDich.size();i++) {
            if (giaoDichMoi.getMagiaodich() == listGiaoDich.get(i).getMagiaodich()) {
                giaoDichCu = listGiaoDich.get(i);
                if (giaoDichMoi.getNhom() == listGiaoDich.get(i).getNhom() &&
                        giaoDichMoi.getNgaygiaodich().equals(listGiaoDich.get(i).getNgaygiaodich())&&
                        giaoDichMoi.getHinhthucphi().equals(listGiaoDich.get(i).getHinhthucphi()) &&
                        giaoDichMoi.getSotien() == listGiaoDich.get(i).getSotien() &&
                        giaoDichMoi.getGhichu().equals(listGiaoDich.get(i).getGhichu()))
                    viewImpGiaoDich.None();

                else {
                    if (giaoDichMoi.getSotien()==0)
                        viewImpGiaoDich.ChuaNhapSoTien();
                    else {
                        for (int j = 0; j < listTaiKhoan.size(); j++)
                            if (listTaiKhoan.get(j).getMataikhoan() == giaoDichMoi.getMataikhoan()) {
                                taiKhoan = listTaiKhoan.get(j);
                                tkt = taiKhoan.getTaikhoanthe();
                                tm = taiKhoan.getTienmat();
                                break;
                            }
                        for(int j = 0;j<listNhom.size();j++)
                            if(giaoDichCu.getNhom()==listNhom.get(j).getManhom()){
                                nhomcu = listNhom.get(j);
                                break;
                            }

                        for(int j = 0;j<listNhom.size();j++)
                            if(giaoDichMoi.getNhom() == listNhom.get(j).getManhom()){
                            nhommoi = listNhom.get(j);
                            }

                        // khôi phục giao dịch cũ
                        if(nhomcu.getLoai()==1 && giaoDichCu.getHinhthucphi().equals("taikhoanthe"))
                             tkt = taiKhoan.getTaikhoanthe() - listGiaoDich.get(i).getSotien();
                        else {
                            if (nhomcu.getLoai() == 1 && giaoDichCu.getHinhthucphi().equals("tienmat"))
                                tm = taiKhoan.getTienmat() - listGiaoDich.get(i).getSotien();
                            else{
                                if (nhomcu.getLoai()==2 && giaoDichCu.getHinhthucphi().equals("taikhoanthe"))
                                    tkt = taiKhoan.getTaikhoanthe()+ listGiaoDich.get(i).getSotien();
                                else{
                                    if (nhomcu.getLoai() == 2 && giaoDichCu.getHinhthucphi().equals("tienmat"))
                                        tm = taiKhoan.getTienmat() + listGiaoDich.get(i).getSotien();
                                }
                            }
                        }
                        // update giao dịch mới
                        if(nhommoi.getLoai()==1 && giaoDichMoi.getHinhthucphi().equals("taikhoanthe"))  //thu nhap vao tai khoan the
                            tkt = tkt + giaoDichMoi.getSotien();
                        else {
                            if (nhommoi.getLoai()==1 && giaoDichMoi.getHinhthucphi().equals("tienmat")) //thu nhập vào tiền mặt
                                tm = tm + giaoDichMoi.getSotien();
                            else{
                                if (nhommoi.getLoai()==2 && giaoDichMoi.getHinhthucphi().equals("taikhoanthe")) //chi tiêu bằng tai khoan thẻ
                                    tkt = tkt - giaoDichMoi.getSotien();
                                else{
                                    if (nhommoi.getLoai()==2 && giaoDichMoi.getHinhthucphi().equals("tienmat"))     //chi tiêu bằng tiền mặt
                                        tm = tm - giaoDichMoi.getSotien();
                                }
                            }
                        }
                        taiKhoan.setTienmat(tm);
                        taiKhoan.setTaikhoanthe(tkt);
                        dbManager.updateTaikhoan(taiKhoan);
                        dbManager.updateGiaoDich(giaoDichMoi);
                        viewImpGiaoDich.SuaGiaoDichThanhCong();
                    }
                }
                break;
            }
        }

    }

    @Override
    public void XoaGiaoDich(GiaoDich giaoDich) {
        DBManager dbManager = new DBManager(MainActivity.getInstance());

       Nhom nhom_su_dung = new Nhom();
       TaiKhoan taiKhoan_su_dung  =new TaiKhoan();
       long tkt =0;
       long tm = 0;
       ArrayList<Nhom> listNhom = dbManager.getNhom(giaoDich.getMataikhoan());
       ArrayList<TaiKhoan> listTaiKhoan = dbManager.getTaiKhoan();
       for(int i = 0;i<listNhom.size();i++){
           if(giaoDich.getNhom() == listNhom.get(i).getManhom()) {
               nhom_su_dung = listNhom.get(i);
               break;
           }
       }
       for(int i = 0;i<listTaiKhoan.size();i++) {
           if (giaoDich.getMataikhoan() == listTaiKhoan.get(i).getMataikhoan()) {
               taiKhoan_su_dung = listTaiKhoan.get(i);
               tkt = taiKhoan_su_dung.getTaikhoanthe();
               tm = taiKhoan_su_dung.getTienmat();
               break;
           }
       }

       if(nhom_su_dung.getLoai()==1 && giaoDich.getHinhthucphi().equals("taikhoanthe"))  //thu nhập vào tài khoản thẻ
                tkt = tkt - giaoDich.getSotien();
       else{
           if (nhom_su_dung.getLoai()==1 && giaoDich.getHinhthucphi().equals("tienmat"))   //thu nhập vào tiền mặt
                    tm = tm - giaoDich.getSotien();
           else{
               if (nhom_su_dung.getLoai()==2 && giaoDich.getHinhthucphi().equals("taikhoanthe"))  //chi tiêu bằng tài khoản thẻ
                        tkt = tkt + giaoDich.getSotien();
               else {
                   if (nhom_su_dung.getLoai()==2 && giaoDich.getHinhthucphi().equals("tienmat"))       //chi tiêu bằng tiền mặt
                            tm = tm + giaoDich.getSotien();
                   }
                }
            }

        taiKhoan_su_dung.setTienmat(tm);
        taiKhoan_su_dung.setTaikhoanthe(tkt);
        dbManager.updateTaikhoan(taiKhoan_su_dung);
        dbManager.deleteGiaoDich(giaoDich);
        viewImpGiaoDich.XoaThanhCong();
    }


    public long ThuNhapHomNay(int ma_tai_khoan)
    {
        long sotien =0;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoNgay(simpleDateFormat.format(calendar.getTime()),ma_tai_khoan);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        for(int i=0;i<list.size();i++)
            for(int j = 0;j<listNhom.size();j++)
            {
                if(list.get(i).getNhom() == listNhom.get(j).getManhom()) {
                    if (listNhom.get(i).getLoai() == 1)
                        sotien = sotien + list.get(i).getSotien();
                    break;
                }
            }

        return sotien;
    }


    public long ThuNhapHomQua(int ma_tai_khoan)
    {
        long sotien =0;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoNgay(simpleDateFormat.format(calendar.getTime()),ma_tai_khoan);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        for(int i=0;i<list.size();i++)
            for(int j = 0;j<listNhom.size();j++)
            {
                if(list.get(i).getNhom() == listNhom.get(j).getManhom()) {
                    if (listNhom.get(i).getLoai() == 1)
                        sotien = sotien + list.get(i).getSotien();
                    break;
                }
            }

        return sotien;
    }
    public long ThuNhapThangNay(int ma_tai_khoan)
    {
        long sotien =0;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoThang(simpleDateFormat.format(date),ma_tai_khoan);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        for(int i=0;i<list.size();i++)
            for(int j = 0;j<listNhom.size();j++)
            {
                if(list.get(i).getNhom() == listNhom.get(j).getManhom()) {
                    if (listNhom.get(j).getLoai() == 1)
                        sotien = sotien + list.get(i).getSotien();
                    break;
                }
            }

        return sotien;
    }
    public long ThuNhapThangTruoc(int ma_tai_khoan)
    {
        long sotien =0;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoThang(simpleDateFormat.format(calendar.getTime()),ma_tai_khoan);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        for(int i=0;i<list.size();i++)
            for(int j = 0;j<listNhom.size();j++)
            {
                if(list.get(i).getNhom() == listNhom.get(j).getManhom()) {
                    if (listNhom.get(j).getLoai() == 1)
                        sotien = sotien + list.get(i).getSotien();
                    break;
                }
            }

        return sotien;
    }


    public long ThuNhapTuanNay(int ma_tai_khoan)
    {
        long sotien =0;
        Date now =new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoTuan(simpleDateFormat.format(now),ma_tai_khoan);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        for(int i=0;i<list.size();i++)
            for(int j = 0;j<listNhom.size();j++)
            {
                if(list.get(i).getNhom() == listNhom.get(j).getManhom()) {
                    if (listNhom.get(j).getLoai() == 1)
                        sotien = sotien + list.get(i).getSotien();
                    break;
                }
            }

        return sotien;
    }
    public long ThuNhapTuanTruoc(int ma_tai_khoan)
    {
        long sotien =0;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR,-1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoTuan(simpleDateFormat.format(calendar.getTime()),ma_tai_khoan);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        for(int i=0;i<list.size();i++)
            for(int j = 0;j<listNhom.size();j++)
            {
                if(list.get(i).getNhom() == listNhom.get(j).getManhom()) {
                    if (listNhom.get(j).getLoai() == 1)
                        sotien = sotien + list.get(i).getSotien();
                    break;
                }
            }

        return sotien;
    }



    public long ChiTieuHomNay(int ma_tai_khoan)
    {
        long sotien =0;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoNgay(simpleDateFormat.format(date),ma_tai_khoan);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        for(int i=0;i<list.size();i++)
            for(int j = 0;j<listNhom.size();j++)
            {
                if(list.get(i).getNhom() == listNhom.get(j).getManhom()) {
                    if (listNhom.get(j).getLoai() == 2)
                        sotien = sotien + list.get(i).getSotien();
                    break;
                }
            }

        return sotien;
    }
    public long ChiTieuHomQua(int ma_tai_khoan)
    {
        long sotien =0;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoNgay(simpleDateFormat.format(calendar.getTime()),ma_tai_khoan);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        for(int i=0;i<list.size();i++)
            for(int j = 0;j<listNhom.size();j++)
            {
                if(list.get(i).getNhom() == listNhom.get(j).getManhom()) {
                    if (listNhom.get(j).getLoai() == 2)
                        sotien = sotien + list.get(i).getSotien();
                    break;
                }
            }

        return sotien;
    }
    public long ChiTieupTuanNay(int ma_tai_khoan)
    {
        long sotien =0;
       Calendar calendar = Calendar.getInstance();
       calendar.add(Calendar.WEEK_OF_YEAR,0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoTuan(simpleDateFormat.format(calendar.getTime()),ma_tai_khoan);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        for(int i=0;i<list.size();i++)
            for(int j = 0;j<listNhom.size();j++)
            {
                if(list.get(i).getNhom() == listNhom.get(j).getManhom()) {
                    if (listNhom.get(j).getLoai() == 2)
                        sotien = sotien + list.get(i).getSotien();
                    break;
                }
            }

        return sotien;
    }
    public long ChiTieupTuanTruoc(int ma_tai_khoan)
    {
        long sotien =0;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR,-1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoTuan(simpleDateFormat.format(calendar.getTime()),ma_tai_khoan);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        for(int i=0;i<list.size();i++)
            for(int j = 0;j<listNhom.size();j++)
            {
                if(list.get(i).getNhom() == listNhom.get(j).getManhom()) {
                    if (listNhom.get(j).getLoai() == 2)
                        sotien = sotien + list.get(i).getSotien();
                    break;
                }
            }

        return sotien;
    }
    public long ChiTieupThangNay(int ma_tai_khoan)
    {
        long sotien =0;
       Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoThang(simpleDateFormat.format(date),ma_tai_khoan);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        for(int i=0;i<list.size();i++)
            for(int j = 0;j<listNhom.size();j++)
            {
                if(list.get(i).getNhom() == listNhom.get(j).getManhom()) {
                    if (listNhom.get(j).getLoai() == 2)
                        sotien = sotien + list.get(i).getSotien();
                    break;
                }
            }

        return sotien;
    }
    public long ChiTieupThangTruoc(int ma_tai_khoan)
    {
        long sotien =0;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<GiaoDich> list = this.XapXepTheoThang(simpleDateFormat.format(calendar.getTime()),ma_tai_khoan);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);
        for(int i=0;i<list.size();i++)
            for(int j = 0;j<listNhom.size();j++)
            {
                if(list.get(i).getNhom() == listNhom.get(j).getManhom()) {
                    if (listNhom.get(j).getLoai() == 2)
                        sotien = sotien + list.get(i).getSotien();
                    break;
                }
            }

        return sotien;
    }


    public long TongThuNhap(int ma_tai_khoan)
    {
        long sotien=0;
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<GiaoDich> listGiaoDich = dbManager.getGiaoDich(ma_tai_khoan);
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);

        for(int i= 0;i<listGiaoDich.size();i++)
            for(int j = 0;j<listNhom.size();j++)
                if(listGiaoDich.get(i).getNhom()==listNhom.get(j).getManhom())
                {
                    if(listNhom.get(j).getLoai()==1)
                        sotien = sotien + listGiaoDich.get(i).getSotien();
                    break;
                }
     return sotien;
    }

    public long TongChiTieu(int ma_tai_khoan)
    {
        long sotien=0;
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<GiaoDich> listGiaoDich = dbManager.getGiaoDich(ma_tai_khoan);
        ArrayList<Nhom> listNhom = dbManager.getNhom(ma_tai_khoan);

        for(int i= 0;i<listGiaoDich.size();i++)
            for(int j = 0;j<listNhom.size();j++)
                if(listGiaoDich.get(i).getNhom()==listNhom.get(j).getManhom())
                {
                    if(listNhom.get(j).getLoai()==2)
                        sotien = sotien + listGiaoDich.get(i).getSotien();
                    break;
                }
        return sotien;
    }


}
