package damthai.com.moneymanagement.View;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import damthai.com.moneymanagement.ImpDangNhapActivity;
import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.Nhom;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.Presenter.PresenterLogicChinhLy;
import damthai.com.moneymanagement.Presenter.PresenterLogicThayDoiSoDu;
import damthai.com.moneymanagement.R;

public class ChinhLyFragment extends Fragment {

    private TaiKhoan taikhoan_using = new TaiKhoan();

    EditText edt_chinhly_taikhoanthe;
    EditText edt_chinhly_tienmat;
    EditText edt_chinhly_tong;
    Button bnt_chinhly_thaydoi_tkt;
    Button bnt_chinhly_thaydoi_tm;
    ListView lv_chinhly_dsnhom;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_chinhly,container,false);
        getTaiKhoan_DangSuDung();

        edt_chinhly_taikhoanthe = (EditText) view.findViewById(R.id.edt_chinhly_taikhoanthe);
        edt_chinhly_tienmat = (EditText) view.findViewById(R.id.edt_chinhly_tienmat);
        edt_chinhly_tong = (EditText) view.findViewById(R.id.edt_chinhly_tong);
        bnt_chinhly_thaydoi_tkt = (Button) view.findViewById(R.id.bnt_chinhly_thaydoi_tkt);
        bnt_chinhly_thaydoi_tm = (Button) view.findViewById(R.id.bnt_chinhly_thaydoi_tm);
        lv_chinhly_dsnhom = (ListView) view.findViewById(R.id.lv_chinhly_danhsachnhom);

        bnt_chinhly_thaydoi_tkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Thay đổi tài khoản thẻ");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_thaydoi_taikhoanthe);
                dialog.show();

                final EditText edt_chinhly_dialog_hientai_taikhoanthe = (EditText) dialog.findViewById(R.id.edt_chinhly_dialog_hientai_taikhoanthe);
                final EditText edt_chinhly_dialog_thaydoi_taikhoanthe = (EditText) dialog.findViewById(R.id.edt_chinhly_dialog_thaydoi_taikhoanthe);
                final Button bnt_chinhly_dialog_taikhoanthe_xacnhan = (Button) dialog.findViewById(R.id.bnt_chinhly_dialog_taikhoanthe_xacnhan);
                final Button bnt_chinhly_dialog_taikhoanthe_huy = (Button) dialog.findViewById(R.id.bnt_chinhly_dialog_taikhoanthe_huy);

                edt_chinhly_dialog_hientai_taikhoanthe.setText(edt_chinhly_taikhoanthe.getText());
                bnt_chinhly_dialog_taikhoanthe_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewThayDoiSoDu  viewThayDoiSoDu = new ViewThayDoiSoDu();
                        PresenterLogicThayDoiSoDu presenterLogicThayDoiSoDu = new PresenterLogicThayDoiSoDu(viewThayDoiSoDu);
                        presenterLogicThayDoiSoDu.XuLyThayDoiSoDu_TaiKhoanThe(taikhoan_using,edt_chinhly_dialog_thaydoi_taikhoanthe.getText().toString());
                        LoadDuLieu();
                        if(viewThayDoiSoDu.getTrangThai()==true)
                            dialog.cancel();
                    }
                });
                bnt_chinhly_dialog_taikhoanthe_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

            }
        });
        bnt_chinhly_thaydoi_tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Thay đổi tiền mặt");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_thaydoi_tienmat);
                dialog.show();

                final EditText edt_chinhly_dialog_hientai_tienmat = (EditText) dialog.findViewById(R.id.edt_chinhly_dialog_hientai_tienmat);
                final EditText edt_chinhly_dialog_thaydoi_tienmat = (EditText) dialog.findViewById(R.id.edt_chinhly_dialog_thaydoi_tienmat);
                final Button bnt_chinhly_dialog_tienmat_xacnhan = (Button) dialog.findViewById(R.id.bnt_chinhly_dialog_tienmat_xacnhan);
                final Button bnt_chinhly_dialog_tienmat_huy = (Button) dialog.findViewById(R.id.bnt_chinhly_dialog_tienmat_huy);

                edt_chinhly_dialog_hientai_tienmat.setText(edt_chinhly_taikhoanthe.getText());
                bnt_chinhly_dialog_tienmat_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ViewThayDoiSoDu  viewThayDoiSoDu = new ViewThayDoiSoDu();
                        PresenterLogicThayDoiSoDu presenterLogicThayDoiSoDu = new PresenterLogicThayDoiSoDu(viewThayDoiSoDu);
                        presenterLogicThayDoiSoDu.XuLyThayDoiSoDu_TienMat(taikhoan_using,edt_chinhly_dialog_thaydoi_tienmat.getText().toString());
                        LoadDuLieu();
                        if(viewThayDoiSoDu.getTrangThai()==true)
                            dialog.cancel();
                    }
                });
                bnt_chinhly_dialog_tienmat_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
            }
        });
        lv_chinhly_dsnhom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, int position, long id) {
                final ViewChinhLy viewChinhLy = new ViewChinhLy();
                final PresenterLogicChinhLy presenterLogicChinhLy = new PresenterLogicChinhLy(viewChinhLy);
                final Nhom nhom;
                 nhom = presenterLogicChinhLy.getNhom(position,taikhoan_using);

                final Dialog dialog = new Dialog(MainActivity.getInstance());
                dialog.setTitle("Thông tin nhóm");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_suanhom);
                dialog.show();
                Button bnt_dialog_suanhom_suanhom = (Button) dialog.findViewById(R.id.bnt_dialog_suanhom_suanhom);
                Button bnt_dialog_suanhom_xoanhom = (Button) dialog.findViewById(R.id.bnt_dialog_suanhom_xoanhom);
                Button bnt_dialog_suanhom_xacnhan = (Button) dialog.findViewById(R.id.bnt_dialog_suanhom_xacnhan);
                EditText edt_dialog_suanhom_tennhom = (EditText) dialog.findViewById(R.id.edt_dialog_suanhom_tennhom);
                EditText edt_dialog_suanhom_loai = (EditText) dialog.findViewById(R.id.edt_dialog_suanhom_loai);
                final LinearLayout linearLayout_dialig_suanhom_nhomsua = (LinearLayout) dialog.findViewById(R.id.linearLayout_dialig_suanhom_nhomsua) ;
                final EditText edt_dialog_suanhom_tennhommoi = (EditText) dialog.findViewById(R.id.edt_dialog_suanhom_tennhommoi);
                final RadioButton rbnt_dialog_suanhom_chitieu = (RadioButton) dialog.findViewById(R.id.rbnt_dialog_suanhom_chitieu);
                final RadioButton rbnt_dialog_suanhom_thunhap = (RadioButton) dialog.findViewById(R.id.rbnt_dialog_suanhom_thunhap);

                edt_dialog_suanhom_tennhom.setText(nhom.getTennhom());
                edt_dialog_suanhom_tennhom.setTextColor(ContextCompat.getColor(MainActivity.getInstance(), R.color.color_ten_nhom));
                if(nhom.getLoai()==1) {
                    edt_dialog_suanhom_loai.setText("Thu nhập");
                    edt_dialog_suanhom_loai.setTextColor(ContextCompat.getColor(MainActivity.getInstance(), R.color.color_thu_nhap));
                }
                if(nhom.getLoai() == 2)
                {
                    edt_dialog_suanhom_loai.setText("Chi tiêu");
                    edt_dialog_suanhom_loai.setTextColor(ContextCompat.getColor(MainActivity.getInstance(), R.color.color_chi_tieu));
                }
                bnt_dialog_suanhom_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(linearLayout_dialig_suanhom_nhomsua.getVisibility()==View.GONE)
                             dialog.cancel();
                        else {
                            nhom.setTennhom(edt_dialog_suanhom_tennhommoi.getText().toString());
                            if(rbnt_dialog_suanhom_thunhap.isChecked() == true)
                                nhom.setLoai(1);
                            else if (rbnt_dialog_suanhom_chitieu.isChecked() == true)
                                nhom.setLoai(2);
                            presenterLogicChinhLy.suaNhom(nhom,taikhoan_using);
                           if(viewChinhLy.getTrangThai() == true)
                               dialog.cancel();
                            LoadDuLieu();
                        }
                    }
                });
                bnt_dialog_suanhom_suanhom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearLayout_dialig_suanhom_nhomsua.setVisibility(View.VISIBLE);
                        edt_dialog_suanhom_tennhommoi.setText(nhom.getTennhom());
                        if(nhom.getLoai() == 1)
                            rbnt_dialog_suanhom_thunhap.setChecked(true);
                        if (nhom.getLoai() == 2)
                            rbnt_dialog_suanhom_chitieu.setChecked(true);
                    }
                });
            }
        });


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
            int taikhoanthe = bundle.getInt("taikhoanthe");
            int tienmat = bundle.getInt("tienmat");
            taikhoan_using.setMataikhoan(mataikhoan);
            taikhoan_using.setTentaikhoan(tentaikhoan);
            taikhoan_using.setMatkhau(matkhau);
            taikhoan_using.setTaikhoanthe(taikhoanthe);
            taikhoan_using.setTienmat(tienmat);
        }
    }
    public void LoadDuLieu()
    {
        ViewChinhLy viewChinhLy = new ViewChinhLy();
        PresenterLogicChinhLy presenterLogicChinhLy = new PresenterLogicChinhLy(viewChinhLy);
        taikhoan_using =  presenterLogicChinhLy.LoadDuLieu_TaiKhoan(taikhoan_using);
        String taikhoanthe = Integer.toString(taikhoan_using.getTaikhoanthe());
        String tienmat = Integer.toString(taikhoan_using.getTienmat());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        edt_chinhly_taikhoanthe.setText(decimalFormat.format(Integer.parseInt(taikhoanthe)));
        edt_chinhly_tienmat.setText(decimalFormat.format(Integer.parseInt(tienmat)));
        int tongtien = taikhoan_using.getTaikhoanthe() + taikhoan_using.getTienmat();
        edt_chinhly_tong.setText(decimalFormat.format(tongtien));
        lv_chinhly_dsnhom = presenterLogicChinhLy.loadDuLiau_ListNhom(lv_chinhly_dsnhom,taikhoan_using);
    }
}
