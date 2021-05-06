package com.example.barcode;

import android.content.Context;
import android.content.Intent;
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
import java.util.List;
import java.util.logging.Handler;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{

    private ArrayList<SavePd> listdata = new ArrayList<>();


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ItemViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        SavePd savePd = listdata.get(i);
        itemViewHolder.ProductName.setText(savePd.getPdName());
        itemViewHolder.ProductDate.setText(savePd.getPdDate());
        Glide.with(itemViewHolder.itemView.getContext()).load(savePd.getPdImg()).into(itemViewHolder.img);
    }


    void addData(SavePd savePd) {
        listdata.add(savePd);
        notifyDataSetChanged();
    }

    void removeItem(int position){
        listdata.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position, listdata.size());
    }

    void setData(ArrayList<SavePd> reset){
        listdata = reset;
        notifyDataSetChanged();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView ProductName;
        private TextView ProductDate;
        private ImageView img;
        private ImageView Item_delete;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            ProductName = itemView.findViewById(R.id.PdName);
            ProductDate = itemView.findViewById(R.id.PdDate);
            img = itemView.findViewById(R.id.imageView);
            Item_delete = itemView.findViewById(R.id.PdDelete);


            Item_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getBindingAdapterPosition();
                    SavePd savePd = listdata.get(pos);

                    if (pos != RecyclerView.NO_POSITION) {
                        removeItem(pos);
                        setData(listdata);
                        Product_Database.getInstance(Item_delete.getContext()).daoSave().delete(savePd);
                    }
                }
            });
        }
    }
}
