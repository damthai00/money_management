package damthai.com.moneymanagement.Model;

import android.content.ContentValues;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.R;

public class NhomAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Nhom> dsnhom;

    public NhomAdapter(Context context, int layout, ArrayList<Nhom> dsnhom){
        this.context = context;
        this.layout = layout;
        this.dsnhom = dsnhom;
    }

    @Override
    public int getCount() {
        return dsnhom.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout,null);
        TextView txt_ten_nhom = (TextView) convertView.findViewById(R.id.txt_dsnhom_tennhom);
        txt_ten_nhom.setText(dsnhom.get(position).getTennhom());
        txt_ten_nhom.setTextColor(ContextCompat.getColor(context,R.color.color_ten_nhom));

        TextView txt_loai= (TextView) convertView.findViewById(R.id.txt_dsnhom_loai);
        if(dsnhom.get(position).getLoai() == 1) {
            txt_loai.setText("Thu nhập");
            txt_loai.setTextColor(ContextCompat.getColor(context, R.color.color_thu_nhap));
        }
        else {
            txt_loai.setText("Chi tiêu");
            txt_loai.setTextColor(ContextCompat.getColor(context,R.color.color_chi_tieu));
        }

        ImageView image_checked = (ImageView) convertView.findViewById(R.id.img_checknhom_dangsudung);
        DBManager dbManager = new DBManager(MainActivity.getInstance());
        ArrayList<GiaoDich> listGiaoDich = dbManager.getGiaoDich(dsnhom.get(position).getMataikhoan());
        for(int i = 0;i<listGiaoDich.size();i++)
            if(dsnhom.get(position).getManhom() == listGiaoDich.get(i).getNhom())
                image_checked.setImageResource(R.drawable.ic_check_nhom_sudung);
        return convertView;
    }
}
