package damthai.com.moneymanagement.Model;

import android.Manifest;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import damthai.com.moneymanagement.MainActivity;
import damthai.com.moneymanagement.R;

public class GiaoDichAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<GiaoDich> dsGiaoDich;

    public GiaoDichAdapter(Context context, int layout, ArrayList<GiaoDich> dsGiaoDich) {
        this.context = context;
        this.layout = layout;
        this.dsGiaoDich = dsGiaoDich;
    }


    @Override
    public int getCount() {
        return dsGiaoDich.size();
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

       TextView txt_danhsach_giaodich_ngay = (TextView) convertView.findViewById(R.id.txt_danhsach_giaodich_ngaythang);
       TextView txt_danhsach_giaoidich_tennhom = (TextView) convertView.findViewById(R.id.txt_danhsach_giaoidich_tennhom);
       TextView txt_danhsach_giaodich_sotien = (TextView) convertView.findViewById(R.id.txt_danhsach_giaoidich_sotien);
        TextView txt_danhsach_giaodich_ghichu = (TextView) convertView.findViewById(R.id.txt_danhsach_giaoidich_ghichu);

       final DBManager dbManager = new DBManager(this.context);
        ArrayList<Nhom> listNhom = new ArrayList<>();
        listNhom = dbManager.getNhom(dsGiaoDich.get(position).getMataikhoan());
        for(int i=0;i<listNhom.size();i++)
            if(listNhom.get(i).getManhom() == dsGiaoDich.get(position).getNhom())
                txt_danhsach_giaoidich_tennhom.setText(listNhom.get(i).getTennhom());

        for(int i=0;i<listNhom.size();i++)
            if(listNhom.get(i).getManhom() == dsGiaoDich.get(position).getNhom()){
                if(listNhom.get(i).getLoai() == 1)
                    txt_danhsach_giaodich_sotien.setTextColor(ContextCompat.getColor(context, R.color.color_thu_nhap));
                if (listNhom.get(i).getLoai() == 2)
                    txt_danhsach_giaodich_sotien.setTextColor(ContextCompat.getColor(context, R.color.color_chi_tieu));
            }

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txt_danhsach_giaodich_ngay.setText(dsGiaoDich.get(position).getNgaygiaodich());
        txt_danhsach_giaodich_ghichu.setText(dsGiaoDich.get(position).getGhichu());
        txt_danhsach_giaodich_sotien.setText(decimalFormat.format(dsGiaoDich.get(position).getSotien()));
        return convertView;
    }
}

