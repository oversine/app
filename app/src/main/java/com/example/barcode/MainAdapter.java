package com.example.barcode;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ItemViewHolder>{

    private ArrayList<SavePd> listdata = new ArrayList<>();


    @NonNull
    @Override
    public MainAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_date, viewGroup, false);
        return new MainAdapter.ItemViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }


    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ItemViewHolder itemViewHolder, int i) {
        SavePd savePd = listdata.get(i);
        itemViewHolder.IconName.setText(savePd.getPdName());
        itemViewHolder.IconDate.setText(savePd.getPdDate());
        Glide.with(itemViewHolder.itemView.getContext()).load(savePd.getPdImg()).into(itemViewHolder.IconImg);
    }

    void addData(SavePd savePd) {
        listdata.add(savePd);
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView IconName, IconDate;
        private ImageView IconImg;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            IconName = itemView.findViewById(R.id.main_date);
            IconDate = itemView.findViewById(R.id.main_dateSet);
            IconImg = itemView.findViewById(R.id.horizon_icon);
        }
    }
}
