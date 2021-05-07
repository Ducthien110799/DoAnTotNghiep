package com.cntt.doantotnghiep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {

    ArrayList<Loaisp> arraylistloaisp;
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> arraylistloaisp, Context context) {
        this.arraylistloaisp = arraylistloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraylistloaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylistloaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txttenloaisp;
        ImageView imgloaisp;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder= null;
        if (view == null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.dong_listview_loaisp, null);
            viewHolder.txttenloaisp= view.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp= view.findViewById(R.id.imageviewloaisp);

            //load dữ liệu
            view.setTag(viewHolder);
        }
        else{
            //mở lại không cần load
            viewHolder= (ViewHolder) view.getTag();

        }

        Loaisp loaisp= (Loaisp) getItem(position);
        viewHolder.txttenloaisp.setText(loaisp.getTenloaisp());
        Picasso.with(context).load(loaisp.getHinhanhloaisp()).into(viewHolder.imgloaisp);

        return view;
    }
}
