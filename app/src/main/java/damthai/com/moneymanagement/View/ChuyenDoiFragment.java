package damthai.com.moneymanagement.View;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.Model.DBManager;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.Presenter.PresenterLogicChuyenDoi;
import damthai.com.moneymanagement.R;

public class ChuyenDoiFragment extends Fragment
{


    private TaiKhoan taikhoan_using = new TaiKhoan();
   private EditText edt_chuyendoi_taikhoanthe;
    EditText edt_chuyendoi_tienmat;
    EditText edt_chuyendoi_sotien;
    EditText edt_chuyendoi_phi;

    RadioButton rbnt_chuyendoi_ruttien;
    RadioButton rbnt_chuyendoi_chuyentien;
    RadioButton rbnt_chuyendoi_taikhoanthe;
    RadioButton rbnt_chuyendoi_tienmat;
    Button bnt_chuyendoi_xacnhan;

   PresenterLogicChuyenDoi presenterLogicChuyenDoi;

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





        bnt_chuyendoi_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewChuyenDoi viewChuyenDoi = new ViewChuyenDoi();
               presenterLogicChuyenDoi = new PresenterLogicChuyenDoi(viewChuyenDoi);
                presenterLogicChuyenDoi.XuLyChuyenDoi(taikhoan_using, rbnt_chuyendoi_ruttien, rbnt_chuyendoi_chuyentien,
                        edt_chuyendoi_sotien,edt_chuyendoi_phi,rbnt_chuyendoi_taikhoanthe,rbnt_chuyendoi_tienmat);

                loadDulieu();
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
            int taikhoanthe = bundle.getInt("taikhoanthe");
            int tienmat = bundle.getInt("tienmat");
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

        String taikhoanthe = Integer.toString(taikhoan_using.getTaikhoanthe());
        String tienmat = Integer.toString(taikhoan_using.getTienmat());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        edt_chuyendoi_taikhoanthe.setText(decimalFormat.format(Integer.parseInt(taikhoanthe)));
        edt_chuyendoi_tienmat.setText(decimalFormat.format(Integer.parseInt(tienmat)));
        edt_chuyendoi_sotien.setText("");
        edt_chuyendoi_phi.setText("");
        edt_chuyendoi_taikhoanthe.setEnabled(false);
        edt_chuyendoi_tienmat.setEnabled(false);
        }
}
