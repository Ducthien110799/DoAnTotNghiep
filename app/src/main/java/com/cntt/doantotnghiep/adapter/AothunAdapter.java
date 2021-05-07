package com.cntt.doantotnghiep.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AothunAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraydienthoai;

    public AothunAdapter(Context context, ArrayList<Sanpham> arraydienthoai) {
        this.context = context;
        this.arraydienthoai = arraydienthoai;
    }

    @Override
    public int getCount() {
        return arraydienthoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arraydienthoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttendienthoai, txtgiadienthoai, txtmotadienthoai;
        public ImageView imgdienthoai;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        //mở lần đầu
        if (view == null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.dong_aothun, null);

            viewHolder.txttendienthoai= view.findViewById(R.id.textviewdienthoai);
            viewHolder.txtgiadienthoai= view.findViewById(R.id.textviewgiadienthoai);
            viewHolder.txtmotadienthoai= view.findViewById(R.id.textviewmotadienthoai);
            viewHolder.imgdienthoai= view.findViewById(R.id.imageviewdienthoai);

            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }

        Sanpham sanpham= (Sanpham) getItem(position);

        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");//set giá cho dễ đọc

        viewHolder.txttendienthoai.setMaxLines(1);
        viewHolder.txttendienthoai.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txttendienthoai.setText(sanpham.getTensanpham());

        viewHolder.txtgiadienthoai.setText(decimalFormat.format(sanpham.getGiasanpham())+" VND");

        //Định dang khi mô tả quá dài
        viewHolder.txtmotadienthoai.setMaxLines(1);//set số dòng
        viewHolder.txtmotadienthoai.setEllipsize(TextUtils.TruncateAt.END);//hiển thị dấu 3 khi còn nữa

        viewHolder.txtmotadienthoai.setText(sanpham.getMotasanpham());
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).into(viewHolder.imgdienthoai);

        return view;
    }
}
