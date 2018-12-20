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
            "mataikhoan" +" TEXT) ";

    private String SQLQuery_CREATETABLE_GIAODICH = " CREATE TABLE "+ " giao_dich "+" ("+
            "magiaodich" +" integer primary key, "+
            "sotien" +" integer, "+
            "nhom" +" integer, " +
            "ngaygiaodich" + " TEXT, " +
            "ghichu" + " TEXT, " +
            "hinhthucphi" + " TEXT, " +
            "mataikhoan" + " integer) ";


    private String SQL_Query_SELECT_ALL_TAIKHOAN = "SELECT * FROM " + TABLE_TAIKHOAN;
    private String SQL_Query_SELECT_ALL_NHOM = "SELECT * FROM " + TABLE_NHOM;
    private String SQL_Query_SELECT_ALL_GIAODICH = "SELECT * FROM " + TABLE_GIAODICH;



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery_CREATETABLE_TAIKHOAN);
        db.execSQL(SQLQuery_CREATETABLE_NHOM);
        db.execSQL(SQLQuery_CREATETABLE_GIAODICH);
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

        int mataikhoan,taikhoanthe,tienmat;
        String tentaikhoan,matkhau;
        cursor.moveToFirst();
        ArrayList<TaiKhoan> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            mataikhoan = cursor.getInt(indexMaTaiKhoan);
            tentaikhoan = cursor.getString(indexTenTaiKhoan);
            matkhau = cursor.getString(indexMatKhau);
            taikhoanthe = cursor.getInt(indexTaiKhoanThe);
            tienmat = cursor.getInt(indexTienMat);
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

        int magiaodich,sotien,nhom,mataikhoan;
        String ngaygiaodich,ghichu,hinhthucphi;

        cursor.moveToFirst();
        ArrayList<GiaoDich> list = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            if (cursor.getInt(indexMaTaiKhoan) != ma_tai_khoan)
                 cursor.moveToNext();
            else
                {
                magiaodich = cursor.getInt(indexMaGiaoDich);
                sotien = cursor.getInt(indexSoTien);
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

}
