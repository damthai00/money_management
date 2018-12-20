package damthai.com.moneymanagement.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.net.ssl.SNIHostName;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.GiaoDich;
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
        PresenterLogicGiaoDich presenterLogicGiaoDich = new PresenterLogicGiaoDich();
        taikhoan_using = presenterLogicGiaoDich.LoadDuLieu_TaiKhoan(taikhoan_using);
        long taikhoanthe = taikhoan_using.getTaikhoanthe();
        long tienmat = taikhoan_using.getTienmat();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        edt_giaodich_taikhoanthe.setText(decimalFormat.format(taikhoanthe));
        edt_giaodich_tienmat.setText(decimalFormat.format(tienmat));
        lv_giaodich_dsgiaodich = presenterLogicGiaoDich.loadDuLiau_DSGiaoDich(lv_giaodich_dsgiaodich,taikhoan_using.getMataikhoan());

        ArrayList<String> list = new ArrayList<>();
        list.add("Tháng này");
        list.add("Tất cả");
        list.add("Hôm nay");
        list.add("10 ngày gần nhất");
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.getInstance(),android.R.layout.simple_spinner_dropdown_item,list);

        spiner_giaodich_loc.setAdapter(arrayAdapter);
    }

}
