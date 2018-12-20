package damthai.com.moneymanagement;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.Presenter.PresenterLogicDangNhap;
import damthai.com.moneymanagement.View.ViewImpDangNhap;

public class ImpDangNhapActivity extends AppCompatActivity implements ViewImpDangNhap {

    EditText edt_user, edt_pass;
    Button bnt_dangnhap,bnt_dangki;
    int KT;

    private static ImpDangNhapActivity instance;
    public static ImpDangNhapActivity getInstance(){
        return instance;
    }

    PresenterLogicDangNhap presenterLogicDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        instance =this;

        bnt_dangnhap = (Button) findViewById(R.id.bnt_dangnhap);
        bnt_dangki = (Button) findViewById(R.id.bnt_dangki);
        edt_user = (EditText) findViewById(R.id.edit_user);
        edt_pass = (EditText) findViewById(R.id.edt_pass);

        presenterLogicDangNhap = new PresenterLogicDangNhap(this);
        bnt_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_user.getText().toString();
                String pass = edt_pass.getText().toString();
                presenterLogicDangNhap.XuLyDangNhap(user,pass);
            }
        });

        bnt_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ImpDangNhapActivity.this);
                dialog.setTitle("Đăng kí tài khoản");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_dang_ki);

               final Button bnt_xacnhan = (Button) dialog.findViewById(R.id.bnt_xacnhan);
               final Button bnt_huy = (Button) dialog.findViewById(R.id.bnt_huy);
               final EditText edt_taikhoandk = (EditText) dialog.findViewById(R.id.edt_taikhoan_dangki);
               final EditText edt_matkhaudk = (EditText) dialog.findViewById(R.id.edt_matkhau_dangki);

                bnt_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = edt_taikhoandk.getText().toString();
                        String pass = edt_matkhaudk.getText().toString();

                        presenterLogicDangNhap.XuLyDangKi(user,pass);

                        if(KT == 1)
                            dialog.cancel();
                    }
                });
                bnt_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ImpDangNhapActivity.this,"Hủy", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }

    public void DangNhapThanhCong(TaiKhoan taiKhoan) {

        Intent intent = new Intent(ImpDangNhapActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();



        bundle.putInt("mataikhoan",taiKhoan.getMataikhoan());
        bundle.putString("tentaikhoan",taiKhoan.getTentaikhoan());
        bundle.putString("matkhau",taiKhoan.getMatkhau());
        bundle.putInt("taikhoanthe",taiKhoan.getTaikhoanthe());
        bundle.putInt("tienmat",taiKhoan.getTienmat());
        intent.putExtra("gettaikhoan",bundle);
        startActivity(intent);
        Toast.makeText(ImpDangNhapActivity.this, "Đăng Nhập Thành Công !" , Toast.LENGTH_SHORT).show();
        edt_user.setText("");
        edt_pass.setText("");
    }

    @Override
    public void DangNhapThatBai() {
        Toast.makeText(ImpDangNhapActivity.this, "Đăng Nhập Thất Bại !" , Toast.LENGTH_SHORT).show();

    }

    public void DangKiThanhCong(){
        Toast.makeText(ImpDangNhapActivity.this,"Đăng kí thành công !", Toast.LENGTH_SHORT).show();
        KT = 1;

    }

    public  void DangKiThatBai(){
        Toast.makeText(ImpDangNhapActivity.this,"Đăng kí thất bại !", Toast.LENGTH_SHORT).show();
        KT = 0;
    }



}
