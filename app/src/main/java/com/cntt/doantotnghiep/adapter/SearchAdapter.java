package com.cntt.doantotnghiep.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.activity.ChiTietSanPhamActivity;
import com.cntt.doantotnghiep.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ItemHolder> implements Filterable {

    Context context;
    ArrayList<Sanpham> arraylistsanpham;
    ArrayList<Sanpham> arrayListSanphamOld;

    public SearchAdapter(Context context, ArrayList<Sanpham> arraylistsanpham) {
        this.context = context;
        this.arraylistsanpham = arraylistsanpham;
        this.arrayListSanphamOld = arraylistsanpham;
    }

    @NonNull
    @Override
    public SearchAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, null);
        SearchAdapter.ItemHolder itemHolder= new SearchAdapter.ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ItemHolder holder, int position) {
        //lấy vị trí sản phẩm
        Sanpham sanpham= arraylistsanpham.get(position);

        holder.txttensanpham.setMaxLines(1);
        holder.txttensanpham.setEllipsize(TextUtils.TruncateAt.END);
        holder.txttensanpham.setText(sanpham.getTensanpham());

        //định dạng cho giá, cứ 3 số thì 1 dấu phẩy
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText(decimalFormat.format(sanpham.getGiasanpham())+" VND");
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).into(holder.imghinhanhsanpham);
        holder.txtmotasanpham.setText(sanpham.getMotasanpham());
    }

    @Override
    public int getItemCount() {
        if (arraylistsanpham !=null){
            return arraylistsanpham.size();
        }
        return 0;
    }


    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhanhsanpham;
        public TextView txttensanpham, txtgiasanpham, txtmotasanpham;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhanhsanpham = (ImageView) itemView.findViewById(R.id.img_sanpham);
            txttensanpham = itemView.findViewById(R.id.tv_tensanpham);
            txtgiasanpham = itemView.findViewById(R.id.tv_giasanpham);
            txtmotasanpham= itemView.findViewById(R.id.tv_motasanpham);

            //set onclick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                    intent.putExtra("thongtinsanpham", arraylistsanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String keySearch = constraint.toString();
                if (keySearch.isEmpty()){
                    arraylistsanpham= arrayListSanphamOld;
                }else{
                    ArrayList<Sanpham> list = new ArrayList<>();
                    for (Sanpham sp: arrayListSanphamOld){
                        if (sp.getTensanpham().toLowerCase().contains(keySearch.toLowerCase())){
                            list.add(sp);
                        }
                    }
                    arraylistsanpham =  list;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values = arraylistsanpham;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arraylistsanpham= (ArrayList<Sanpham>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
