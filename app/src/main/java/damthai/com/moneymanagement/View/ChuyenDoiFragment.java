package damthai.com.moneymanagement.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.LoaderManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.ChuyenDoi;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.Presenter.PresenterLogicChuyenDoi;
import damthai.com.moneymanagement.Presenter.PresenterLogicGiaoDich;
import damthai.com.moneymanagement.R;

public class ChuyenDoiFragment extends Fragment
{


    private TaiKhoan taikhoan_using = new TaiKhoan();
   private EditText edt_chuyendoi_taikhoanthe;
    EditText edt_chuyendoi_tienmat;
    EditText edt_chuyendoi_sotien;
    EditText edt_chuyendoi_phi;
    EditText edt_chuyendoi_ngaychuyendoi;
    EditText edt_chuyendoi_ghichu;

    RadioButton rbnt_chuyendoi_ruttien;
    RadioButton rbnt_chuyendoi_chuyentien;
    RadioButton rbnt_chuyendoi_taikhoanthe;
    RadioButton rbnt_chuyendoi_tienmat;
    Button bnt_chuyendoi_xacnhan;
    ListView lv_danh_sach_chuyendoi;
    Button bnt_chuyendoi_chonngay;
    ChuyenDoi chuyenDoiMoi;


    ViewChuyenDoi viewChuyenDoi = new ViewChuyenDoi();
    PresenterLogicChuyenDoi presenterLogicChuyenDoi = new PresenterLogicChuyenDoi(viewChuyenDoi);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chuyendoi,container,false);
        Bundle bundle = getArguments();
        getTaiKhoan_DangSuDung();


        edt_chuyendoi_taikhoanthe = (EditText) view.findViewById(R.id.edt_chuyendoi_taikhoanthe);
        edt_chuyendoi_tienmat = (EditText) view.findViewById(R.id.edt_chuyendoi_tienmat);
        edt_chuyendoi_sotien = (EditText) view.findViewById(R.id.edt_chuyendoi_sotien);
        edt_chuyendoi_phi = (EditText) view.findViewById(R.id.edt_chuyendoi_phi);
        rbnt_chuyendoi_chuyentien = (RadioButton) view.findViewById(R.id.rbnt_chuyendoi_chuyentien);
        rbnt_chuyendoi_ruttien = (RadioButton) view.findViewById(R.id.rbnt_chuyendoi_ruttien);
        rbnt_chuyendoi_taikhoanthe = (RadioButton) view.findViewById(R.id.rbnt_chuyendoi_taikhoanthe);
        rbnt_chuyendoi_tienmat = (RadioButton) view.findViewById(R.id.rbnt_chuyendoi_tienmat);
        bnt_chuyendoi_xacnhan = (Button) view.findViewById(R.id.bnt_chuyendoi_xacnhan);
        lv_danh_sach_chuyendoi = (ListView) view.findViewById(R.id.lv_danhsach_chuyendoi);
        edt_chuyendoi_ngaychuyendoi = (EditText) view.findViewById(R.id.edt_chuyendoi_ngaychuyendoi);
        edt_chuyendoi_ghichu = (EditText) view.findViewById(R.id.edt_chuyendoi_ghichu);
        bnt_chuyendoi_chonngay = (Button) view.findViewById(R.id.bnt_chuyendoi_chonngay);


        bnt_chuyendoi_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenterLogicChuyenDoi.XuLyChuyenDoi(taikhoan_using, rbnt_chuyendoi_ruttien, rbnt_chuyendoi_chuyentien,
                        edt_chuyendoi_sotien,edt_chuyendoi_phi,rbnt_chuyendoi_taikhoanthe,rbnt_chuyendoi_tienmat,
                        edt_chuyendoi_ngaychuyendoi,edt_chuyendoi_ghichu);

                loadDulieu();
            }
        });

        bnt_chuyendoi_chonngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(calendar.DATE);
                int thang = calendar.get(calendar.MONTH);
                int nam = calendar.get(calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        edt_chuyendoi_ngaychuyendoi.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },nam,thang,ngay);
                datePickerDialog.show();
            }
        });

        lv_danh_sach_chuyendoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               final ChuyenDoi chuyenDoi;
               chuyenDoi = presenterLogicChuyenDoi.getItemChuyenDoi(position,taikhoan_using.getMataikhoan());
                final Dialog dialog = new Dialog(MainActivity.getInstance());
                dialog.setTitle("Thông tin chuyển đổi");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_sua_chuyendoi);
                dialog.show();
                chuyenDoiMoi = chuyenDoi;

                final TextView txt_dialog_sua_chuyendoi_ten = (TextView) dialog.findViewById(R.id.txt_dialog_sua_chuyendoi_ten);
                final EditText edt_dialog_sua_chuyendoi_sotien = (EditText) dialog.findViewById(R.id.edt_dialog_sua_chuyendoi_sotien);
                final EditText edt_dialog_sua_chuyendoi_phi = (EditText) dialog.findViewById(R.id.edt_dialog_sua_chuyendoi_phi);
                final EditText edt_dialog_sua_chuyendoi_ghichu = (EditText) dialog.findViewById(R.id.edt_dialog_sua_chuyendoi_ghichu);
                final EditText edt_dialog_sua_chuyendoi_ngaychuyendoi = (EditText) dialog.findViewById(R.id.edt_dialog_sua_chuyendoi_ngaychuyendoi);
                final RadioButton rbnt_dialog_sua_chuyendoi_tiemmat = (RadioButton) dialog.findViewById(R.id.rbnt_dialog_sua_chuyendoi_tiemmat);
                final RadioButton rbnt_dialog_sua_chuyendoi_taikhoanthe = (RadioButton) dialog.findViewById(R.id.rbnt_dialog_sua_chuyendoi_taikhoanthe);
                final RadioButton rbnt_dialog_sua_chuyendoi_chuyentien = (RadioButton) dialog.findViewById(R.id.rbnt_dialog_sua_chuyendoi_chuyentien);
                final RadioButton rbnt_dialog_sua_chuyendoi_ruttien = (RadioButton) dialog.findViewById(R.id.rbnt_dialog_sua_chuyendoi_ruttien);
                final Button bnt_dialog_sua_chuyendoi_chonngay = (Button) dialog.findViewById(R.id.bnt_dialog_sua_chuyendoi_chonngay);
                final Button bnt_dialog_sua_chuyendoi_huy = (Button) dialog.findViewById(R.id.bnt_dialog_sua_chuyendoi_huy);
                final Button bnt_dialog_sua_chuyendoi_sua = (Button) dialog.findViewById(R.id.bnt_dialog_sua_chuyendoi_sua);
                Button bnt_dialog_sua_chuyendoi_xoa = (Button) dialog.findViewById(R.id.bnt_dialog_sua_chuyendoi_xoa);
                Button bnt_dialog_sua_chuyendoi_xacnhan = (Button) dialog.findViewById(R.id.bnt_dialog_sua_chuyendoi_xacnhan);
                final LinearLayout linear_hinhthuc = (LinearLayout) dialog.findViewById(R.id.linear_hinhthuc);

                if(chuyenDoi.getHinhthuc().equals("chuyentien")) {
                    txt_dialog_sua_chuyendoi_ten.setText("Chuyển tiền");
                    rbnt_dialog_sua_chuyendoi_chuyentien.setChecked(true);
                }else if (chuyenDoi.getHinhthuc().equals("ruttien")){
                    txt_dialog_sua_chuyendoi_ten.setText("Rút tiền");
                    rbnt_dialog_sua_chuyendoi_ruttien.setChecked(true);
                }
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                edt_dialog_sua_chuyendoi_sotien.setText(decimalFormat.format(chuyenDoi.getSotien()));
                edt_dialog_sua_chuyendoi_phi.setText(decimalFormat.format(chuyenDoi.getPhi()));
                edt_dialog_sua_chuyendoi_ghichu.setText(chuyenDoi.getGhichu());
                edt_dialog_sua_chuyendoi_ngaychuyendoi.setText(chuyenDoi.getNgaychuyendoi());
                if(chuyenDoi.getLoaiphi().equals("tienmat")) {
                    rbnt_dialog_sua_chuyendoi_tiemmat.setChecked(true);
                }else
                    if(chuyenDoi.getLoaiphi().equals("taikhoanthe"))
                        rbnt_dialog_sua_chuyendoi_taikhoanthe.setChecked(true);

                bnt_dialog_sua_chuyendoi_chonngay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewGiaoDich viewGiaoDich = new ViewGiaoDich();
                        PresenterLogicGiaoDich presenterLogicGiaoDich = new PresenterLogicGiaoDich(viewGiaoDich);
                        String StringDate = edt_dialog_sua_chuyendoi_ngaychuyendoi.getText().toString();
                        int ngay = presenterLogicGiaoDich.LayNgayTrongChuoi(StringDate);
                        int thang = presenterLogicGiaoDich.LayThangTrongChuoi(StringDate);
                        int nam = presenterLogicGiaoDich.LayNamTrongChuoi(StringDate);
                        final Calendar calendar = Calendar.getInstance();

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(year,month,dayOfMonth);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                edt_dialog_sua_chuyendoi_ngaychuyendoi.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        },nam,thang,ngay);
                        datePickerDialog.show();
                    }
                });

                bnt_dialog_sua_chuyendoi_sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txt_dialog_sua_chuyendoi_ten.setVisibility(View.GONE);
                        linear_hinhthuc.setVisibility(View.VISIBLE);
                        rbnt_dialog_sua_chuyendoi_chuyentien.setEnabled(true);
                        rbnt_dialog_sua_chuyendoi_ruttien.setEnabled(true);
                        rbnt_dialog_sua_chuyendoi_tiemmat.setEnabled(true);
                        rbnt_dialog_sua_chuyendoi_taikhoanthe.setEnabled(true);
                        edt_dialog_sua_chuyendoi_sotien.setEnabled(true);
                        edt_dialog_sua_chuyendoi_phi.setEnabled(true);
                        edt_dialog_sua_chuyendoi_ghichu.setEnabled(true);
                        bnt_dialog_sua_chuyendoi_chonngay.setEnabled(true);
                        bnt_dialog_sua_chuyendoi_huy.setVisibility(View.VISIBLE);
                        bnt_dialog_sua_chuyendoi_sua.setVisibility(View.GONE);
                        edt_dialog_sua_chuyendoi_sotien.setText(String.valueOf(chuyenDoi.getSotien()));
                        edt_dialog_sua_chuyendoi_phi.setText(String.valueOf(chuyenDoi.getPhi()));
                    }
                });
                bnt_dialog_sua_chuyendoi_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(edt_dialog_sua_chuyendoi_sotien.isEnabled()==false)
                            dialog.cancel();
                        else {
                            if(edt_dialog_sua_chuyendoi_sotien.getText().toString().equals(""))
                                chuyenDoiMoi.setSotien(0);
                            else chuyenDoiMoi.setSotien(Long.parseLong(edt_dialog_sua_chuyendoi_sotien.getText().toString()));
                            if(edt_dialog_sua_chuyendoi_phi.getText().toString().equals(""))
                                chuyenDoiMoi.setPhi(0);
                            else chuyenDoiMoi.setPhi(Long.parseLong(edt_dialog_sua_chuyendoi_phi.getText().toString()));
                            chuyenDoiMoi.setMataikhoan(taikhoan_using.getMataikhoan());
                            chuyenDoiMoi.setNgaychuyendoi(edt_dialog_sua_chuyendoi_ngaychuyendoi.getText().toString());
                            chuyenDoiMoi.setGhichu(edt_dialog_sua_chuyendoi_ghichu.getText().toString());
                            if(rbnt_dialog_sua_chuyendoi_chuyentien.isChecked()==true)
                                chuyenDoiMoi.setHinhthuc("chuyentien");
                            if(rbnt_dialog_sua_chuyendoi_ruttien.isChecked()==true)
                                chuyenDoiMoi.setHinhthuc("ruttien");
                            if(rbnt_dialog_sua_chuyendoi_taikhoanthe.isChecked()==true)
                                chuyenDoiMoi.setLoaiphi("taikhoanthe");
                            if(rbnt_dialog_sua_chuyendoi_tiemmat.isChecked()==true)
                                chuyenDoiMoi.setLoaiphi("tienmat");

                            presenterLogicChuyenDoi.SuaChuyenDoi(chuyenDoiMoi);
                            if(viewChuyenDoi.getTrangThai()==true) {
                                dialog.cancel();
                                loadDulieu();
                            }
                        }

                    }
                });

                bnt_dialog_sua_chuyendoi_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                bnt_dialog_sua_chuyendoi_xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      presenterLogicChuyenDoi.XoaChuyenDoi(chuyenDoiMoi);
                      if(viewChuyenDoi.getTrangThai()==true)
                      {
                          dialog.cancel();
                          loadDulieu();
                      }
                    }
                });
            }
        });

        loadDulieu();
        return view;
    }
    public void getTaiKhoan_DangSuDung(){
        Bundle bundle = getArguments();
        if(bundle == null)
            Toast.makeText(getActivity(),"Lỗi load dữ liệu !",Toast.LENGTH_SHORT).show();
        else
        {
            int mataikhoan = bundle.getInt("mataikhoan");
            String tentaikhoan = bundle.getString("tentaikhoan");
            String matkhau = bundle.getString("matkhau");
            long taikhoanthe = bundle.getLong("taikhoanthe");
            long tienmat = bundle.getLong("tienmat");
            taikhoan_using.setMataikhoan(mataikhoan);
            taikhoan_using.setTentaikhoan(tentaikhoan);
            taikhoan_using.setMatkhau(matkhau);
            taikhoan_using.setTaikhoanthe(taikhoanthe);
            taikhoan_using.setTienmat(tienmat);
        }
    }

    public void loadDulieu(){
        PresenterLogicChuyenDoi presenterLogicChuyenDoi = new PresenterLogicChuyenDoi();
        taikhoan_using  = presenterLogicChuyenDoi.LoadDuLieu(taikhoan_using);

        String taikhoanthe = Long.toString(taikhoan_using.getTaikhoanthe());
        String tienmat = Long.toString(taikhoan_using.getTienmat());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        edt_chuyendoi_taikhoanthe.setText(decimalFormat.format(Long.parseLong(taikhoanthe)));
        edt_chuyendoi_tienmat.setText(decimalFormat.format(Long.parseLong(tienmat)));
        edt_chuyendoi_sotien.setText("");
        edt_chuyendoi_phi.setText("");
        edt_chuyendoi_taikhoanthe.setEnabled(false);
        edt_chuyendoi_tienmat.setEnabled(false);
       lv_danh_sach_chuyendoi = presenterLogicChuyenDoi.LoadDanhSachChuyenDoi(lv_danh_sach_chuyendoi,taikhoan_using.getMataikhoan());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendarInstance = Calendar.getInstance();
        edt_chuyendoi_ngaychuyendoi.setText(simpleDateFormat.format(calendarInstance.getTime()));
        }
}
