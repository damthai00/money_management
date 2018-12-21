package damthai.com.moneymanagement.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.GiaoDich;
import damthai.com.moneymanagement.Model.Nhom;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.Presenter.PresenterImpThem;
import damthai.com.moneymanagement.Presenter.PresenterLogicChinhLy;
import damthai.com.moneymanagement.Presenter.PresenterLogicTaoNhom;
import damthai.com.moneymanagement.Presenter.PresenterLogicThem;
import damthai.com.moneymanagement.R;


public class ThemFragment extends Fragment {

    private TaiKhoan taikhoan_using = new TaiKhoan();
    private Nhom nhom_dc_chom = new Nhom();

    EditText edt_them_sotien,edt_them_nhom,edt_them_ghichu,edt_them_ngay;
    RadioButton rbnt_them_tienmat,rbnt_them_taikhoanthe;
    Button bnt_them_taolai,bnt_them_them;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_them,container,false);
        getTaiKhoan_DangSuDung();

        Button bnt_them_chonnhom = (Button) view.findViewById(R.id.bnt_them_chonnhom);
        Button bnt_them_chonngay = (Button) view.findViewById(R.id.bnt_them_chonngay);
        edt_them_sotien = (EditText) view.findViewById(R.id.edt_them_sotien);
        edt_them_nhom = (EditText) view.findViewById(R.id.edt_them_nhom);
        edt_them_ngay = (EditText) view.findViewById(R.id.edt_them_ngay);

        edt_them_ghichu = (EditText) view.findViewById(R.id.edt_them_ghichu);
        rbnt_them_tienmat = (RadioButton) view.findViewById(R.id.rbnt_them_tienmat);
        rbnt_them_taikhoanthe = (RadioButton) view.findViewById(R.id.rbnt_them_taikhoanthe);
        bnt_them_taolai = (Button) view.findViewById(R.id.bnt_them_taolai);
        bnt_them_them = (Button) view.findViewById(R.id.bnt_them_them);

        bnt_them_chonnhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Chọn nhóm");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_chonnhom);

                final Button bnt_chonnhom_huy = (Button) dialog.findViewById(R.id.bnt_chonnhom_huy);
                final Button bnt_chonnhom_taonhommoi = (Button) dialog.findViewById(R.id.bnt_chonnhom_taonhommoi);
                final ListView lv_chonnhom_dsnhom = (ListView) dialog.findViewById(R.id.lv_chonnhom_dsnhom);
                LoadDSNhom(lv_chonnhom_dsnhom,taikhoan_using);

                bnt_chonnhom_huy.setOnClickListener(new View.OnClickListener() {
                        @Override
                     public void onClick(View v) {
                         dialog.cancel();;
                     }
                 });
                bnt_chonnhom_taonhommoi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       final Dialog dialog = new Dialog(getActivity());
                        dialog.setTitle("Tạo nhóm mới");
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.dialog_taonhom);
                        dialog.show();

                        final Button bnt_taonhom_xacnhan = (Button) dialog.findViewById(R.id.bnt_taonhom_xacnhan);
                        bnt_taonhom_xacnhan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EditText edt_taonhom_tennhom = (EditText) dialog.findViewById(R.id.edt_taonhom_tennhom);
                                RadioButton rbnt_taonhom_thunhap = (RadioButton) dialog.findViewById(R.id.rbnt_taonhom_thunhap);
                                RadioButton rbnt_taonhom_chitieu = (RadioButton) dialog.findViewById(R.id.rbnt_taonhom_chitieu);

                                ViewTaoNhom viewTaoNhom = new ViewTaoNhom();
                                PresenterLogicTaoNhom presenterLogicTaoNhom = new PresenterLogicTaoNhom(viewTaoNhom);
                                presenterLogicTaoNhom.XuLyTaoNhom(edt_taonhom_tennhom,rbnt_taonhom_thunhap,rbnt_taonhom_chitieu,taikhoan_using);
                                if (viewTaoNhom.getTrangThai() == true)
                                    dialog.cancel();
                                LoadDSNhom(lv_chonnhom_dsnhom,taikhoan_using);
                            }
                        });

                        final Button bnt_taonhom_huy = (Button) dialog.findViewById(R.id.bnt_taonhom_huy);
                        bnt_taonhom_huy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                            }
                        });
                    }
                });

                lv_chonnhom_dsnhom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final ViewChinhLy viewChinhLy = new ViewChinhLy();
                        final PresenterLogicChinhLy presenterLogicChinhLy = new PresenterLogicChinhLy(viewChinhLy);
                        nhom_dc_chom = presenterLogicChinhLy.getNhom(position,taikhoan_using);
                        if (nhom_dc_chom.getLoai()==1)
                            edt_them_nhom.setTextColor(ContextCompat.getColor(MainActivity.getInstance(), R.color.color_thu_nhap));
                        if (nhom_dc_chom.getLoai()==2)
                            edt_them_nhom.setTextColor(ContextCompat.getColor(MainActivity.getInstance(), R.color.color_chi_tieu));
                        edt_them_nhom.setText(nhom_dc_chom.getTennhom());
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendarInstance = Calendar.getInstance();
        edt_them_ngay.setText(simpleDateFormat.format(calendarInstance.getTime()));
        bnt_them_chonngay.setOnClickListener(new View.OnClickListener() {
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
                        edt_them_ngay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },nam,thang,ngay);
                datePickerDialog.show();
            }
        });

        bnt_them_taolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_them_sotien.setText("");
                edt_them_nhom.setText("");
                edt_them_ghichu.setText("");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendarInstance = Calendar.getInstance();
                edt_them_ngay.setText(simpleDateFormat.format(calendarInstance.getTime()));
                rbnt_them_tienmat.setChecked(false);
                rbnt_them_taikhoanthe.setChecked(false);
            }
        });

        bnt_them_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewThem viewThem = new ViewThem();
                PresenterLogicThem presenterLogicThem = new PresenterLogicThem(viewThem);
                int nhom,mataikhoan;
                long sotien;
                String ngaygiaodich,ghichu,hinhthucphi = "";

                if(edt_them_sotien.getText().toString().equals(""))
                    sotien = -1;
                else sotien = (Long.parseLong(edt_them_sotien.getText().toString()));
                if(edt_them_nhom.getText().toString().equals(""))
                 {
                     nhom = -1;
                 }
                 else nhom = nhom_dc_chom.getManhom();
                 mataikhoan = taikhoan_using.getMataikhoan();
                 ngaygiaodich = edt_them_ngay.getText().toString();
                 ghichu = edt_them_ghichu.getText().toString();
                 if(rbnt_them_tienmat.isChecked() == false && rbnt_them_taikhoanthe.isChecked() == false)
                     hinhthucphi ="";
                 else{
                     if(rbnt_them_taikhoanthe.isChecked() == true)
                         hinhthucphi = "taikhoanthe";
                     else
                        if(rbnt_them_tienmat.isChecked() == true)
                         hinhthucphi = "tienmat";
                 }

                GiaoDich giaoDich = new GiaoDich(sotien,nhom,ngaygiaodich,ghichu,hinhthucphi,mataikhoan);
                presenterLogicThem.ThemGiaoDich(giaoDich);

                if(viewThem.getTrangThai() == true)
                    TaoLai();
            }
        });

        return view;
    }

    public void LoadDSNhom(ListView listView,TaiKhoan taiKhoan)
    {
        ViewThem viewThem = new ViewThem();
        PresenterLogicThem presenterLogicThem = new PresenterLogicThem(viewThem);
        listView = presenterLogicThem.LoadDSNhom(listView,taiKhoan);
    }

    public void TaoLai()
    {
        edt_them_sotien.setText("");
        edt_them_nhom.setText("");
        edt_them_ghichu.setText("");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendarInstance = Calendar.getInstance();
        edt_them_ngay.setText(simpleDateFormat.format(calendarInstance.getTime()));
        rbnt_them_tienmat.setChecked(false);
        rbnt_them_taikhoanthe.setChecked(false);
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

}
