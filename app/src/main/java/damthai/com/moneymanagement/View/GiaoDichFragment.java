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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.SNIHostName;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.GiaoDich;
import damthai.com.moneymanagement.Model.Nhom;
import damthai.com.moneymanagement.Presenter.PresenterLogicChinhLy;
import damthai.com.moneymanagement.Presenter.PresenterLogicGiaoDich;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.Presenter.PresenterLogicTaoNhom;
import damthai.com.moneymanagement.Presenter.PresenterLogicThem;
import damthai.com.moneymanagement.R;

public class GiaoDichFragment extends Fragment {

    TaiKhoan taikhoan_using = new TaiKhoan();
    ListView lv_giaodich_dsgiaodich;
    Spinner spiner_giaodich_loc;
    EditText edt_giaodich_taikhoanthe,edt_giaodich_tienmat;
    Nhom nhom_dc_chom;
    Nhom nhom;
    GiaoDich giaoDichDangChon;
    EditText edt_giaodich_tongthu,edt_giaodich_tongchi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giaodich,container,false);
        edt_giaodich_taikhoanthe = (EditText) view.findViewById(R.id.edt_giaodich_taikhoanthe);
        edt_giaodich_tienmat = (EditText) view.findViewById(R.id.edt_giaodich_tienmat);
        lv_giaodich_dsgiaodich = (ListView) view.findViewById(R.id.lv_giaodich_dsgiaodich);
        spiner_giaodich_loc = (Spinner) view.findViewById(R.id.spiner_giaodich_loc);
        edt_giaodich_tongthu = (EditText) view.findViewById(R.id.edt_giaodich_tongthu);
        edt_giaodich_tongchi = (EditText) view.findViewById(R.id.edt_giaodich_tongchi);
        LoadDuLieu();
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

    public void LoadDuLieu()
    {
        getTaiKhoan_DangSuDung();
        final ViewGiaoDich viewGiaoDich = new ViewGiaoDich();
        final PresenterLogicGiaoDich presenterLogicGiaoDich = new PresenterLogicGiaoDich(viewGiaoDich);
        taikhoan_using = presenterLogicGiaoDich.LoadDuLieu_TaiKhoan(taikhoan_using);
        final long taikhoanthe = taikhoan_using.getTaikhoanthe();
        final long tienmat = taikhoan_using.getTienmat();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        edt_giaodich_taikhoanthe.setText(decimalFormat.format(taikhoanthe));
        edt_giaodich_tienmat.setText(decimalFormat.format(tienmat));


        ArrayList<String> list = new ArrayList<>();
        list.add("Tuần này");   //0
        list.add("Hôm nay");    //1
        list.add("Tháng này");  //2
        list.add("Tuần trước"); //3
        list.add("Tháng trước");//4
        list.add("Tất cả");     //5
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.getInstance(),android.R.layout.simple_spinner_dropdown_item,list);
        spiner_giaodich_loc.setAdapter(arrayAdapter);

        spiner_giaodich_loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    lv_giaodich_dsgiaodich = presenterLogicGiaoDich.GiaoDichTheoTuan(lv_giaodich_dsgiaodich,taikhoan_using.getMataikhoan());
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    edt_giaodich_tongthu.setText(decimalFormat.format(presenterLogicGiaoDich.ThuNhapTuanNay(taikhoan_using.getMataikhoan())));
                    edt_giaodich_tongchi.setText(decimalFormat.format(presenterLogicGiaoDich.ChiTieupTuanNay(taikhoan_using.getMataikhoan())));
                }
                if(position == 1) {
                    lv_giaodich_dsgiaodich = presenterLogicGiaoDich.GiaoDichTheoNgay(lv_giaodich_dsgiaodich, taikhoan_using.getMataikhoan());
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    edt_giaodich_tongthu.setText(decimalFormat.format(presenterLogicGiaoDich.ThuNhapHomNay(taikhoan_using.getMataikhoan())));
                    edt_giaodich_tongchi.setText(decimalFormat.format(presenterLogicGiaoDich.ChiTieuHomNay(taikhoan_using.getMataikhoan())));
                }
                if(position == 2) {
                    lv_giaodich_dsgiaodich = presenterLogicGiaoDich.GiaoDichTheoThang(lv_giaodich_dsgiaodich, taikhoan_using.getMataikhoan());
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    edt_giaodich_tongthu.setText(decimalFormat.format(presenterLogicGiaoDich.ThuNhapThangNay(taikhoan_using.getMataikhoan())));
                    edt_giaodich_tongchi.setText(decimalFormat.format(presenterLogicGiaoDich.ChiTieupThangNay(taikhoan_using.getMataikhoan())));
                }
                if(position == 4){
                    lv_giaodich_dsgiaodich = presenterLogicGiaoDich.GiaoDichTheoThang_Truoc(lv_giaodich_dsgiaodich,taikhoan_using.getMataikhoan());
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    edt_giaodich_tongthu.setText(decimalFormat.format(presenterLogicGiaoDich.ThuNhapThangTruoc(taikhoan_using.getMataikhoan())));
                    edt_giaodich_tongchi.setText(decimalFormat.format(presenterLogicGiaoDich.ChiTieupThangTruoc(taikhoan_using.getMataikhoan())));
                }

                if(position == 3){
                    lv_giaodich_dsgiaodich = presenterLogicGiaoDich.GiaoDichTheoTuan_Truoc(lv_giaodich_dsgiaodich,taikhoan_using.getMataikhoan());
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    edt_giaodich_tongthu.setText(decimalFormat.format(presenterLogicGiaoDich.ThuNhapTuanTruoc(taikhoan_using.getMataikhoan())));
                    edt_giaodich_tongchi.setText(decimalFormat.format(presenterLogicGiaoDich.ChiTieupTuanTruoc(taikhoan_using.getMataikhoan())));
                }

                if(position == 5){
                    lv_giaodich_dsgiaodich = presenterLogicGiaoDich.loadDuLiau_DSGiaoDich(lv_giaodich_dsgiaodich,taikhoan_using.getMataikhoan());
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    edt_giaodich_tongthu.setText(decimalFormat.format(presenterLogicGiaoDich.TongThuNhap(taikhoan_using.getMataikhoan())));
                    edt_giaodich_tongchi.setText(decimalFormat.format(presenterLogicGiaoDich.TongChiTieu(taikhoan_using.getMataikhoan())));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        lv_giaodich_dsgiaodich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                GiaoDich giaoDich;
                giaoDich = new GiaoDich();

                if(spiner_giaodich_loc.getSelectedItemPosition()==0) {
                   giaoDich = presenterLogicGiaoDich.SuaGiaoDich_TuanNay(position, taikhoan_using.getMataikhoan());
                }
                if(spiner_giaodich_loc.getSelectedItemPosition()==1) {
                    giaoDich = presenterLogicGiaoDich.SuaGiaoDich_HomNay(position, taikhoan_using.getMataikhoan());
                }
                if(spiner_giaodich_loc.getSelectedItemPosition()==2) {
                    giaoDich = presenterLogicGiaoDich.SuaGiaoDich_ThangNay(position, taikhoan_using.getMataikhoan());

                }
                if(spiner_giaodich_loc.getSelectedItemPosition()==5) {
                    giaoDich = presenterLogicGiaoDich.SuaGiaoDich_TatCa(position, taikhoan_using.getMataikhoan());

                }
                if(spiner_giaodich_loc.getSelectedItemPosition()==3) {
                    giaoDich = presenterLogicGiaoDich.SuaGiaoDich_TuanTruoc(position, taikhoan_using.getMataikhoan());

                }
                if(spiner_giaodich_loc.getSelectedItemPosition()==4) {
                    giaoDich = presenterLogicGiaoDich.SuaGiaoDich_ThangTruoc(position, taikhoan_using.getMataikhoan());

                }
                giaoDichDangChon = giaoDich;
                nhom = presenterLogicGiaoDich.getNhom(giaoDich);
                nhom_dc_chom=nhom;
                final Dialog dialog = new Dialog(MainActivity.getInstance());
                dialog.setTitle("Thông tin giao dịch");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_suagiaodich);
                dialog.show();

                final Button bnt_dialog_suagiaodich_sua = (Button) dialog.findViewById(R.id.bnt_dialog_suagiaodich_sua);
                Button bnt_dialog_suagiaodich_xoa = (Button) dialog.findViewById(R.id.bnt_dialog_suagiaodich_xoa);
                Button bnt_dialog_suagiaodich_xacnhan = (Button) dialog.findViewById(R.id.bnt_dialog_suagiaodich_xacnhan);
                final Button bnt_dialog_suagiaodich_huy = (Button) dialog.findViewById(R.id.bnt_dialog_suagiaodich_huy);
                final Button bnt_dialog_suagiaodich_chonnhom = (Button) dialog.findViewById(R.id.bnt_dialog_suagiaodich_chonnhom);
                final Button bnt_dialog_suagiaodich_chongnay = (Button) dialog.findViewById(R.id.bnt_dialog_suagiaodich_chongnay);
                final RadioButton rbnt_dialog_suagiaodich_tienmat = (RadioButton) dialog.findViewById(R.id.rbnt_dialog_suagiaodich_tienmat);
                final RadioButton rbnt_dialog_suagiaodich_taikhoanthe = (RadioButton) dialog.findViewById(R.id.rbnt_dialog_suagiaodich_taikhoanthe);
                final EditText edt_dialog_suagiaodich_sotien = (EditText) dialog.findViewById(R.id.edt_dialog_suagiaodich_sotien);
                final EditText edt_dialog_suagiaodich_nhom = (EditText) dialog.findViewById(R.id.edt_dialog_suagiaodich_nhom);
                final EditText edt_dialog_suagiaodich_ngay = (EditText) dialog.findViewById(R.id.edt_dialog_suagiaodich_ngay);
                final EditText edt_dialog_suagiaodich_ghichu = (EditText) dialog.findViewById(R.id.edt_dialog_suagiaodich_ghichu);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                edt_dialog_suagiaodich_sotien.setText(decimalFormat.format(giaoDich.getSotien()));
                edt_dialog_suagiaodich_nhom.setText(nhom.getTennhom());
                edt_dialog_suagiaodich_ghichu.setText(giaoDich.getGhichu());
                edt_dialog_suagiaodich_ngay.setText(giaoDich.getNgaygiaodich());
                if(giaoDich.getHinhthucphi().equals("tienmat")) {
                    rbnt_dialog_suagiaodich_tienmat.setChecked(true);
                }
                if (giaoDich.getHinhthucphi().equals("taikhoanthe")) {
                    rbnt_dialog_suagiaodich_taikhoanthe.setChecked(true);
                }
                if (nhom.getLoai()==1) {
                    edt_dialog_suagiaodich_nhom.setTextColor(ContextCompat.getColor(MainActivity.getInstance(), R.color.color_thu_nhap));
                }

                if (nhom.getLoai()==2) {
                    edt_dialog_suagiaodich_nhom.setTextColor(ContextCompat.getColor(MainActivity.getInstance(), R.color.color_chi_tieu));
                }


                bnt_dialog_suagiaodich_sua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt_dialog_suagiaodich_sotien.setEnabled(true);
                        edt_dialog_suagiaodich_sotien.setText(String.valueOf(giaoDichDangChon.getSotien()));
                        edt_dialog_suagiaodich_ghichu.setEnabled(true);
                        bnt_dialog_suagiaodich_chonnhom.setEnabled(true);
                        bnt_dialog_suagiaodich_chongnay.setEnabled(true);
                        rbnt_dialog_suagiaodich_tienmat.setEnabled(true);
                        rbnt_dialog_suagiaodich_taikhoanthe.setEnabled(true);
                        edt_dialog_suagiaodich_sotien.setText(edt_dialog_suagiaodich_sotien.getText());
                        edt_dialog_suagiaodich_sotien.setTextColor(ContextCompat.getColor(MainActivity.getInstance(), R.color.color_den));

                        bnt_dialog_suagiaodich_huy.setVisibility(View.VISIBLE);
                        bnt_dialog_suagiaodich_sua.setVisibility(View.GONE);
                    }
                });
                bnt_dialog_suagiaodich_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                bnt_dialog_suagiaodich_chonnhom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(getActivity());
                        dialog.setTitle("Chọn nhóm");
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.dialog_chonnhom);
                        dialog.show();

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
                                        if (viewTaoNhom.getTrangThai() == true) {
                                            dialog.cancel();
                                            LoadDSNhom(lv_chonnhom_dsnhom,taikhoan_using);
                                        }

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
                                    edt_dialog_suagiaodich_nhom.setTextColor(ContextCompat.getColor(MainActivity.getInstance(), R.color.color_thu_nhap));
                                if (nhom_dc_chom.getLoai()==2)
                                    edt_dialog_suagiaodich_nhom.setTextColor(ContextCompat.getColor(MainActivity.getInstance(), R.color.color_chi_tieu));
                                edt_dialog_suagiaodich_nhom.setText(nhom_dc_chom.getTennhom());
                                dialog.cancel();
                            }
                        });

                    }
                });

                bnt_dialog_suagiaodich_chongnay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String StringDate = edt_dialog_suagiaodich_ngay.getText().toString();
                        int ngay = presenterLogicGiaoDich.LayNgayTrongChuoi(StringDate);
                         int thang = presenterLogicGiaoDich.LayThangTrongChuoi(StringDate);
                        int nam = presenterLogicGiaoDich.LayNamTrongChuoi(StringDate);
                        final Calendar calendar = Calendar.getInstance();

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(year,month,dayOfMonth);
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                edt_dialog_suagiaodich_ngay.setText(simpleDateFormat.format(calendar.getTime()));
                            }
                        },nam,thang,ngay);
                        datePickerDialog.show();
                    }
                });

                bnt_dialog_suagiaodich_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        GiaoDich giaoDichMoi = new GiaoDich();

                        if(edt_dialog_suagiaodich_sotien.isEnabled()==false)
                            dialog.cancel();
                        else if(edt_dialog_suagiaodich_sotien.isEnabled()) {
                            giaoDichMoi.setMagiaodich(giaoDichDangChon.getMagiaodich());
                            String sotien = edt_dialog_suagiaodich_sotien.getText().toString();
                            if(sotien.equals(""))
                                giaoDichMoi.setSotien(0);
                            else{giaoDichMoi.setSotien(Long.parseLong(edt_dialog_suagiaodich_sotien.getText().toString()));
                            }
                            giaoDichMoi.setNhom(nhom_dc_chom.getManhom());
                            giaoDichMoi.setGhichu(edt_dialog_suagiaodich_ghichu.getText().toString());
                            giaoDichMoi.setMataikhoan(giaoDichDangChon.getMataikhoan());
                            giaoDichMoi.setNgaygiaodich(edt_dialog_suagiaodich_ngay.getText().toString());
                            if (rbnt_dialog_suagiaodich_taikhoanthe.isChecked() == true)
                                giaoDichMoi.setHinhthucphi("taikhoanthe");
                            else if (rbnt_dialog_suagiaodich_tienmat.isChecked() == true)
                                giaoDichMoi.setHinhthucphi("tienmat");
                            presenterLogicGiaoDich.SuaDoiGiaoDich(giaoDichMoi);

                            if(viewGiaoDich.getTrangThai()==true) {
                                dialog.cancel();
                                LoadDuLieu();
                            }
                        }



                    }
                });

                bnt_dialog_suagiaodich_xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenterLogicGiaoDich.XoaGiaoDich(giaoDichDangChon);
                        if(viewGiaoDich.getTrangThai()==true) {
                            dialog.cancel();
                            LoadDuLieu();
                        }
                    }
                });
            }

        });
    }

    public void LoadDSNhom(ListView listView,TaiKhoan taiKhoan)
    {
        ViewThem viewThem = new ViewThem();
        PresenterLogicThem presenterLogicThem = new PresenterLogicThem(viewThem);
        listView = presenterLogicThem.LoadDSNhom(listView,taiKhoan);
    }

}
