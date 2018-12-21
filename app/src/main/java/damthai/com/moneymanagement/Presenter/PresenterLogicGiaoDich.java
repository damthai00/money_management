package damthai.com.moneymanagement.Presenter;

import android.icu.text.UnicodeSetSpanner;
import android.net.ParseException;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.security.PublicKey;
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
}
