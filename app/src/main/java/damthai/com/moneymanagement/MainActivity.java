package damthai.com.moneymanagement;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;


import damthai.com.moneymanagement.Model.GiaoDichAdapter;
import damthai.com.moneymanagement.Model.TaiKhoan;
import damthai.com.moneymanagement.View.ChinhLyFragment;
import damthai.com.moneymanagement.View.ChuyenDoiFragment;
import damthai.com.moneymanagement.View.GiaoDichFragment;
import damthai.com.moneymanagement.View.ThemFragment;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;
    public static MainActivity getInstance(){
        return instance;
    }
    private TaiKhoan taikhoan_using = new TaiKhoan();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        getTaiKhoan_DangSuDung();



        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        Bundle bundle = new Bundle();
        bundle = truyen_dulieu();
        android.support.v4.app.Fragment giaoDichFragment = null;
        giaoDichFragment = new GiaoDichFragment();
        giaoDichFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,giaoDichFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    android.support.v4.app.Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.giaodich: {
                           selectedFragment = new GiaoDichFragment();
                            break;
                        }
                        case R.id.them: {
                            selectedFragment = new ThemFragment();
                            break;
                        }
                        case R.id.chuyendoi: {
                            selectedFragment = new ChuyenDoiFragment();
                            break;
                        }
                        case R.id.chinhly: {
                            selectedFragment = new ChinhLyFragment();
                            break;
                        }
                    }
                    Bundle bundle = new Bundle();
                    bundle = truyen_dulieu();
                    selectedFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };
    public void getTaiKhoan_DangSuDung(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("gettaikhoan");
        if(bundle == null)
            Toast.makeText(this,"Lỗi load dữ liệu !",Toast.LENGTH_SHORT).show();
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

    public Bundle truyen_dulieu(){
        Bundle bundle = new Bundle();
        bundle.putInt("mataikhoan",taikhoan_using.getMataikhoan());
        bundle.putString("tentaikhoan",taikhoan_using.getTentaikhoan());
        bundle.putString("matkhau",taikhoan_using.getMatkhau());
        bundle.putLong("taikhoanthe",taikhoan_using.getTaikhoanthe());
        bundle.putLong("tienmat",taikhoan_using.getTienmat());
        return bundle;
    }

    public void setTaikhoan_using(long taikhoanthe,long tienmat)
    {
        this.taikhoan_using.setTaikhoanthe(taikhoanthe);
        this.taikhoan_using.setTienmat(tienmat);
    }

}







