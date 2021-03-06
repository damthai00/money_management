package damthai.com.moneymanagement.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.content.ContentValues;

import java.util.ArrayList;



public class  DBManager extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "quan_ly_chi_tieu";
    private static final String TABLE_TAIKHOAN = "tai_khoan";
    private static final String TABLE_GIAODICH = "giao_dich";
    private static final String TABLE_NHOM = "nhom";
    private static final String TABLE_CHUYENDOI= "chuyendoi";

    private static final String ID = "mataikhoan";
    private static final String NAME = "tentaikhoan";
    private static final String PASS = "matkhau";
    private static final String TKT = "taikhoanthe";
    private static final String TM = "tienmat";


    private static final int version = 1;


    private Context context;

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
        this.context =context;
    }


    private String SQLQuery_CREATETABLE_TAIKHOAN = "CREATE TABLE "+TABLE_TAIKHOAN +" ("+
            ID +" integer primary key, "+
            NAME +" TEXT, "+
            PASS +" TEXT, "+
            TKT + " integer, "+
            TM + " integer)";

    private String SQLQuery_CREATETABLE_NHOM = " CREATE TABLE "+ " nhom "+" ("+
            "manhom" +" integer primary key, "+
            "tennhom" +" TEXT, "+
            "loai" +" integer, "+
            "mataikhoan" +" integer) ";

    private String SQLQuery_CREATETABLE_GIAODICH = " CREATE TABLE "+ " giao_dich "+" ("+
            "magiaodich" +" integer primary key, "+
            "sotien" +" integer, "+
            "nhom" +" integer, " +
            "ngaygiaodich" + " TEXT, " +
            "ghichu" + " TEXT, " +
            "hinhthucphi" + " TEXT, " +
            "mataikhoan" + " integer) ";

    private String SQLQuery_CREATETABLE_CHUYENDOI = " CREATE TABLE "+ " chuyendoi "+" ("+
            "machuyendoi" +" integer primary key, "+
            "sotien" +" integer, "+
            "phi" +" integer, "+
            "loaiphi" +" TEXT, "+
            "hinhthuc" +" TEXT, "+
            "ngaychuyendoi" +" TEXT, "+
            "ghichu" +" TEXT, "+
            "mataikhoan" + " integer) ";


    private String SQL_Query_SELECT_ALL_TAIKHOAN = "SELECT * FROM " + TABLE_TAIKHOAN;
    private String SQL_Query_SELECT_ALL_NHOM = "SELECT * FROM " + TABLE_NHOM;
    private String SQL_Query_SELECT_ALL_GIAODICH = "SELECT * FROM " + TABLE_GIAODICH;
    private String SQL_Query_SELECT_ALL_CHUYENDOI = "SELECT * FROM " + TABLE_CHUYENDOI;



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery_CREATETABLE_TAIKHOAN);
        db.execSQL(SQLQuery_CREATETABLE_NHOM);
        db.execSQL(SQLQuery_CREATETABLE_GIAODICH);
        db.execSQL(SQLQuery_CREATETABLE_CHUYENDOI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //Xử lý tài khoản -  -- - - - -----------------------------------------------------------------------------------------
    public ArrayList<TaiKhoan> getTaiKhoan(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor  = db.rawQuery(SQL_Query_SELECT_ALL_TAIKHOAN,null);
        if(cursor == null)
            return null;
        int indexMaTaiKhoan = cursor.getColumnIndex(ID);
        int indexTenTaiKhoan = cursor.getColumnIndex(NAME);
        int indexMatKhau = cursor.getColumnIndex(PASS);
        int indexTaiKhoanThe = cursor.getColumnIndex(TKT);
        int indexTienMat = cursor.getColumnIndex(TM);

        int mataikhoan;
        long taikhoanthe,tienmat;
        String tentaikhoan,matkhau;
        cursor.moveToFirst();
        ArrayList<TaiKhoan> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            mataikhoan = cursor.getInt(indexMaTaiKhoan);
            tentaikhoan = cursor.getString(indexTenTaiKhoan);
            matkhau = cursor.getString(indexMatKhau);
            taikhoanthe = cursor.getLong(indexTaiKhoanThe);
            tienmat = cursor.getLong(indexTienMat);
            list.add(new TaiKhoan(mataikhoan,tentaikhoan,matkhau,taikhoanthe,tienmat));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public void addTaiKhoan(TaiKhoan taiKhoan){
       SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME,taiKhoan.getTentaikhoan());
        values.put(PASS,taiKhoan.getMatkhau());
        values.put(TKT,0);
        values.put(TM,0);

        db.insert(TABLE_TAIKHOAN,null,values);
        db.close();
    }

    /// Thay đổi số dư tài khoản
    public void updateTaikhoan(TaiKhoan taiKhoan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentaikhoan",taiKhoan.getTentaikhoan());
        contentValues.put("matkhau",taiKhoan.getMatkhau());
        contentValues.put("taikhoanthe",taiKhoan.getTaikhoanthe());
        contentValues.put("tienmat",taiKhoan.getTienmat());
        db.update(TABLE_TAIKHOAN,contentValues,"mataikhoan ="+taiKhoan.getMataikhoan(),null);
        db.close();
    }

    //Xử lý với nhóm----------------------------------------------------------------------------------------------------

    public ArrayList<Nhom> getNhom(int ma_tai_khoan){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor  = db.rawQuery(SQL_Query_SELECT_ALL_NHOM,null);
        if(cursor == null)
            return null;
        int indexMaNhom = cursor.getColumnIndex("manhom");
        int indexTenNhom = cursor.getColumnIndex("tennhom");
        int indexMataikhoan = cursor.getColumnIndex("mataikhoan");
        int indexLoai = cursor.getColumnIndex("loai");
        int manhom,loai,mataikhoan;
        String tennhom;
        cursor.moveToFirst();
        ArrayList<Nhom> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(indexMataikhoan) != ma_tai_khoan)
                cursor.moveToNext();
            else {
                manhom = cursor.getInt(indexMaNhom);
                tennhom = cursor.getString(indexTenNhom);
                loai = cursor.getInt(indexLoai);
                mataikhoan = cursor.getInt(indexMataikhoan);
                list.add(new Nhom(manhom, tennhom, loai, mataikhoan));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return list;
    }

    public void addNhom(Nhom nhom)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =new ContentValues();

        values.put("tennhom", nhom.getTennhom());
        values.put("mataikhoan",nhom.getMataikhoan());
        values.put("loai",nhom.getLoai());
        db.insert(TABLE_NHOM,null,values);
        db.close();
    }

    public void updateNhom(Nhom nhom) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tennhom",nhom.getTennhom());
        contentValues.put("mataikhoan",nhom.getMataikhoan());
        contentValues.put("loai",nhom.getLoai());
        db.update(TABLE_NHOM,contentValues,"manhom ="+nhom.getManhom(),null);
        db.close();
    }

    public void deleteNhom(Nhom nhom)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NHOM,"manhom = " +nhom.getManhom(),null );
        db.close();
    }

    //Xử lý Giao dịch -----------------------------------------------------------------------------------------------------------

    public ArrayList<GiaoDich> getGiaoDich(int ma_tai_khoan)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor  = db.rawQuery(SQL_Query_SELECT_ALL_GIAODICH,null);
        if(cursor == null)
            return null;
        int indexMaGiaoDich = cursor.getColumnIndex("magiaodich");
        int indexSoTien  = cursor.getColumnIndex("sotien");
        int indexNhom = cursor.getColumnIndex("nhom");
        int indexNgayGiaoDich = cursor.getColumnIndex("ngaygiaodich");
        int indexGhiChu = cursor.getColumnIndex("ghichu");
        int indexHinhThucPhi = cursor.getColumnIndex("hinhthucphi");
        int indexMaTaiKhoan = cursor.getColumnIndex("mataikhoan");

        int magiaodich,nhom,mataikhoan;
        long sotien;
        String ngaygiaodich,ghichu,hinhthucphi;

        cursor.moveToFirst();
        ArrayList<GiaoDich> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(indexMaTaiKhoan) != ma_tai_khoan)
                 cursor.moveToNext();
            else
                {
                magiaodich = cursor.getInt(indexMaGiaoDich);
                sotien = cursor.getLong(indexSoTien);
                nhom = cursor.getInt(indexNhom);
                ngaygiaodich = cursor.getString(indexNgayGiaoDich);
                ghichu = cursor.getString(indexGhiChu);
                hinhthucphi = cursor.getString(indexHinhThucPhi);
                mataikhoan = cursor.getInt(indexMaTaiKhoan);
                list.add(new GiaoDich(magiaodich, sotien, nhom, ngaygiaodich, ghichu, hinhthucphi, mataikhoan));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return list;
    }

    public void addGiaoDich(GiaoDich giaoDich){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sotien",giaoDich.getSotien());
        values.put("nhom",giaoDich.getNhom());
        values.put("ngaygiaodich",giaoDich.getNgaygiaodich());
        values.put("ghichu",giaoDich.getGhichu());
        values.put("hinhthucphi",giaoDich.getHinhthucphi());
        values.put("mataikhoan",giaoDich.getMataikhoan());
        db.insert(TABLE_GIAODICH,null,values);
        db.close();
    }

    // thay đổi giao dịch
    public void updateGiaoDich(GiaoDich giaoDich) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sotien",giaoDich.getSotien());
        contentValues.put("nhom",giaoDich.getNhom());
        contentValues.put("ngaygiaodich",giaoDich.getNgaygiaodich());
        contentValues.put("ghichu",giaoDich.getGhichu());
        contentValues.put("hinhthucphi",giaoDich.getHinhthucphi());
        contentValues.put("mataikhoan",giaoDich.getMataikhoan());
        db.update(TABLE_GIAODICH,contentValues,"magiaodich ="+giaoDich.getMagiaodich(),null);
        db.close();
    }

    public void deleteGiaoDich(GiaoDich giaoDich)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GIAODICH,"magiaodich = " +giaoDich.getMagiaodich(),null );
        db.close();
    }

    //Xử lý chuyển đổi---------------------------------------------------------------------------

    public ArrayList<ChuyenDoi> getChuyenDoi(int ma_tai_khoan){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor  = db.rawQuery(SQL_Query_SELECT_ALL_CHUYENDOI,null);
        if(cursor == null)
            return null;
        int indexMaChuyenDoi = cursor.getColumnIndex("machuyendoi");
        int indexSoTien = cursor.getColumnIndex("sotien");
        int indexPhi = cursor.getColumnIndex("phi");
        int indexLoaiPhi = cursor.getColumnIndex("loaiphi");
        int indexHinhThuc = cursor.getColumnIndex("hinhthuc");
        int indexNgayChuyenDoi = cursor.getColumnIndex("ngaychuyendoi");
        int indexGhiChu = cursor.getColumnIndex("ghichu");

        int indexMataikhoan = cursor.getColumnIndex("mataikhoan");
        int machuyendoi,mataikhoan;
        long sotien,phi;
        String loaiphi,hinhthuc,ngaychuyendoi,ghichu;

        cursor.moveToFirst();
        ArrayList<ChuyenDoi> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(indexMataikhoan) != ma_tai_khoan)
                cursor.moveToNext();
            else {
                machuyendoi = cursor.getInt(indexMaChuyenDoi);
                sotien = cursor.getLong(indexSoTien);
                phi = cursor.getLong(indexPhi);
                loaiphi =cursor.getString(indexLoaiPhi);
                hinhthuc = cursor.getString(indexHinhThuc);
                ngaychuyendoi = cursor.getString(indexNgayChuyenDoi);
                ghichu = cursor.getString(indexGhiChu);
                mataikhoan = cursor.getInt(indexMataikhoan);
                list.add(new ChuyenDoi(machuyendoi, sotien, phi, loaiphi, hinhthuc,ngaychuyendoi,ghichu, mataikhoan));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return list;
    }

    public void addChuyenDoi(ChuyenDoi chuyenDoi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sotien",chuyenDoi.getSotien());
        values.put("phi",chuyenDoi.getPhi());
        values.put("loaiphi",chuyenDoi.getLoaiphi());
        values.put("hinhthuc",chuyenDoi.getHinhthuc());
        values.put("ngaychuyendoi",chuyenDoi.getNgaychuyendoi());
        values.put("ghichu",chuyenDoi.getGhichu());
        values.put("mataikhoan",chuyenDoi.getMataikhoan());
        db.insert(TABLE_CHUYENDOI,null,values);
        db.close();
    }

    public void updateChuyenDoi(ChuyenDoi chuyenDoi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sotien",chuyenDoi.getSotien());
        contentValues.put("phi",chuyenDoi.getPhi());
        contentValues.put("loaiphi",chuyenDoi.getLoaiphi());
        contentValues.put("hinhthuc",chuyenDoi.getHinhthuc());
        contentValues.put("ngaychuyendoi",chuyenDoi.getNgaychuyendoi());
        contentValues.put("ghichu",chuyenDoi.getGhichu());
        contentValues.put("mataikhoan",chuyenDoi.getMataikhoan());
        db.update(TABLE_CHUYENDOI,contentValues,"machuyendoi ="+chuyenDoi.getMachuyendoi(),null);
        db.close();
    }

    public void deleteChuyenDoi(ChuyenDoi chuyenDoi)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CHUYENDOI,"machuyendoi = " +chuyenDoi.getMachuyendoi(),null );
        db.close();
    }

}
