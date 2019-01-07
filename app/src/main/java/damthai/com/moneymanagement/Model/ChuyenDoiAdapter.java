package damthai.com.moneymanagement.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import damthai.com.moneymanagement.R;

public class ChuyenDoiAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<ChuyenDoi> dsChuyenDoi;
    @Override
    public int getCount() {
        return dsChuyenDoi.size();
    }
    public ChuyenDoiAdapter(Context context, int layout, ArrayList<ChuyenDoi> dsChuyenDoi) {
        this.context = context;
        this.layout = layout;
        this.dsChuyenDoi = dsChuyenDoi;
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

        TextView txt_danh_sach_chuyendoi_ngaythang = (TextView) convertView.findViewById(R.id.txt_danh_sach_chuyendoi_ngaythang);
        TextView txt_danh_sach_chuyendoi_hinhthuc = (TextView) convertView.findViewById(R.id.txt_danh_sach_chuyendoi_hinhthuc);
        TextView txt_danh_sach_chuyendoi_phi = (TextView) convertView.findViewById(R.id.txt_danh_sach_chuyendoi_phi);
        TextView txt_danh_sach_chuyendoi_ghichu = (TextView) convertView.findViewById(R.id.txt_danh_sach_chuyendoi_ghichu);
        TextView txt_danh_sach_chuyendoi_sotien = (TextView) convertView.findViewById(R.id.txt_danh_sach_chuyendoi_sotien);
        TextView txt_danh_sach_chuyendoi_loaiphi = (TextView) convertView.findViewById(R.id.txt_danh_sach_chuyendoi_loaiphi);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txt_danh_sach_chuyendoi_ngaythang.setText(dsChuyenDoi.get(position).getNgaychuyendoi());
        if(dsChuyenDoi.get(position).getHinhthuc().equals("chuyentien"))
            txt_danh_sach_chuyendoi_hinhthuc.setText("Chuyển tiền");
        else if(dsChuyenDoi.get(position).getHinhthuc().equals("ruttien"))
            txt_danh_sach_chuyendoi_hinhthuc.setText("Rút tiền");
        txt_danh_sach_chuyendoi_ghichu.setText(dsChuyenDoi.get(position).getGhichu());
        txt_danh_sach_chuyendoi_sotien.setText(decimalFormat.format(dsChuyenDoi.get(position).getSotien()));
        txt_danh_sach_chuyendoi_phi.setText(decimalFormat.format(dsChuyenDoi.get(position).getPhi()));
        if(dsChuyenDoi.get(position).getPhi()==0)
            txt_danh_sach_chuyendoi_loaiphi.setText("");
        else  {
            if(dsChuyenDoi.get(position).getLoaiphi().equals("taikhoanthe"))
                txt_danh_sach_chuyendoi_loaiphi.setText("TK Thẻ");
            else if(dsChuyenDoi.get(position).getLoaiphi().equals("tienmat"))
                txt_danh_sach_chuyendoi_loaiphi.setText("Tiền mặt");
        }
        return convertView;
    }
}

