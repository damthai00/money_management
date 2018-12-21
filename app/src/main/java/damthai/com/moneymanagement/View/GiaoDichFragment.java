package damthai.com.moneymanagement.View;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.SNIHostName;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.GiaoDich;
import damthai.com.moneymanagement.Model.Nhom;
import damthai.com.moneymanagement.Presenter.PresenterLogicGiaoDich;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.R;

public class GiaoDichFragment extends Fragment {

    TaiKhoan taikhoan_using = new TaiKhoan();
    ListView lv_giaodich_dsgiaodich;
    Spinner spiner_giaodich_loc;
    EditText edt_giaodich_taikhoanthe,edt_giaodich_tienmat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giaodich,container,false);
        edt_giaodich_taikhoanthe = (EditText) view.findViewById(R.id.edt_giaodich_taikhoanthe);
        edt_giaodich_tienmat = (EditText) view.findViewById(R.id.edt_giaodich_tienmat);
        lv_giaodich_dsgiaodich = (ListView) view.findViewById(R.id.lv_giaodich_dsgiaodich);
        spiner_giaodich_loc = (Spinner) view.findViewById(R.id.spiner_giaodich_loc);
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
        final PresenterLogicGiaoDich presenterLogicGiaoDich = new PresenterLogicGiaoDich();
        taikhoan_using = presenterLogicGiaoDich.LoadDuLieu_TaiKhoan(taikhoan_using);
        final long taikhoanthe = taikhoan_using.getTaikhoanthe();
        long tienmat = taikhoan_using.getTienmat();
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
                }

                if(position == 1)
                  lv_giaodich_dsgiaodich = presenterLogicGiaoDich.GiaoDichTheoNgay(lv_giaodich_dsgiaodich,taikhoan_using.getMataikhoan());
                if(position == 2)
                    lv_giaodich_dsgiaodich = presenterLogicGiaoDich.GiaoDichTheoThang(lv_giaodich_dsgiaodich,taikhoan_using.getMataikhoan());
                if(position == 4)
                    lv_giaodich_dsgiaodich = presenterLogicGiaoDich.GiaoDichTheoThang_Truoc(lv_giaodich_dsgiaodich,taikhoan_using.getMataikhoan());
                if(position == 3)
                    lv_giaodich_dsgiaodich = presenterLogicGiaoDich.GiaoDichTheoTuan_Truoc(lv_giaodich_dsgiaodich,taikhoan_using.getMataikhoan());
                if(position == 5)
                    lv_giaodich_dsgiaodich = presenterLogicGiaoDich.loadDuLiau_DSGiaoDich(lv_giaodich_dsgiaodich,taikhoan_using.getMataikhoan());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        lv_giaodich_dsgiaodich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GiaoDich giaoDich = new GiaoDich();
                Nhom nhom = new Nhom();
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

                nhom = presenterLogicGiaoDich.getNhom(giaoDich);
                final Dialog dialog = new Dialog(MainActivity.getInstance());
                dialog.setTitle("Thông tin giao dịch");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_suagiaodich);
                dialog.show();
            }
        });
    }

}
